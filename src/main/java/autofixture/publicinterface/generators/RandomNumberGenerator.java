package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;
import autofixture.publicinterface.ObjectCreationException;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * Ported from https://github.com/AutoFixture/AutoFixture
 */

public class RandomNumberGenerator implements InstanceGenerator {
    private final List<Long> limits;
    private final Object syncRoot; //TODO handle sync root
    private final Random random;
    private final HashSet<Long> numbers;
    private long lower;
    private long upper;
    private long count;


    public RandomNumberGenerator() {
        this(Lists.newArrayList(
                Long.valueOf(1),
                Long.valueOf(Byte.MAX_VALUE),
                Long.valueOf(Short.MAX_VALUE),
                Long.valueOf(Integer.MAX_VALUE)));
    }

    public RandomNumberGenerator(List<Long> limits) {
        if (limits == null) {
            throw new NullPointerException("limits");
        }

        if (limits.size() < 2) {
            throw new IllegalArgumentException("The limit must be a sequence of two or more integers.");
        }

        this.limits = limits;
        this.syncRoot = new Object();
        this.random = new Random();
        this.numbers = new HashSet<>();
        this.createRange();
    }

    public Iterable<Long> getLimits() {
        return this.limits;
    }

    private Long getNextRandom() {
        //lock (this.syncRoot)
        //{
        this.evaluateRange();

        long result;
        do {
            if (this.lower >= Integer.MIN_VALUE && this.upper <= Integer.MAX_VALUE) {
                result = this.random.nextInt((int) this.upper - (int) this.lower) + (int) this.lower;
            } else {
                result = this.getNextInt64InRange();
            }
        }
        while (this.numbers.contains(result));

        this.numbers.add(result);
        return result;
        //}
    }

    private void evaluateRange() {
        if (this.count == (this.upper - this.lower)) {
            this.count = 0;
            this.createRange();
        }

        this.count++;
    }

    private void createRange() {
        Collection<Long> remaining = Collections2.filter(this.limits, new Predicate<Long>() {
            @Override
            public boolean apply(Long x) {
                return x > upper - 1;
            }
        });

        if (remaining.size() > 0 && this.numbers.size() > 0) {
            this.lower = this.upper;
            this.upper = Ordering.<Long>natural().min(remaining) + 1;
        } else {
            this.lower = limits.get(0);
            this.upper = limits.get(1);
        }

        this.numbers.clear();
    }

    private long getNextInt64InRange() {
        long range = this.upper - this.lower;
        long limit = Long.MAX_VALUE - Long.MAX_VALUE % range;
        long number;
        do {
            byte[] buffer = new byte[8];
            this.random.nextBytes(buffer);
            number = bytesToLong(buffer);
        } while (number > limit);
        return number % range + this.lower;
    }

    @Override
    public <T> boolean appliesTo(InstanceType<T> instanceType) {
        return instanceType.isCompatibleWithAnyOf(
                Byte.class,
                Character.class,
                Integer.class,
                Long.class,
                Short.class);
    }

    @Override
    public <T> T next(InstanceType<T> instanceType, FixtureContract fixture) {
        try {
            if(instanceType.isCompatibleWith(Integer.class)) {
                return (T)Integer.valueOf(this.getNextRandom().intValue());
            } else if(instanceType.isCompatibleWith(Short.class)) {
                return (T)Short.valueOf(getNextRandom().shortValue());
            } else if(instanceType.isCompatibleWith(Long.class)) {
                return (T)getNextRandom();
            } else if(instanceType.isCompatibleWith(Byte.class)) {
                return (T)Byte.valueOf(getNextRandom().byteValue());
            } else if(instanceType.isCompatibleWith(Character.class)) {
                return (T)Character.valueOf(new String(
                        new byte[] {getNextRandom().byteValue()}).charAt(0));
            }
            return (T) this.getNextRandom();
        } catch (Exception e) {
            throw new ObjectCreationException(instanceType, e);
        }
    }

    @Override
    public void setOmittingAutoProperties(boolean isOn) {

    }

    private long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.put(bytes);
        buffer.flip();
        return buffer.getLong();
    }
}

