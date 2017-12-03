package autofixture.specification.acceptance;

import autofixture.publicinterface.Fixture;
import autofixture.publicinterface.InstanceOf;
import autofixture.publicinterface.thirdpartysupport.AnyVavr;
import io.vavr.collection.*;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import lombok.val;
import org.junit.Test;

import static autofixture.specification.acceptance.GenerationAssertions.*;
import static autofixture.specification.acceptance.TypeHelpers.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class VavrCollectionGenerationSpecification {

  private final Fixture fixture = new Fixture();

  @Test
  public void shouldGenerateImmutableMaps() {

    io.vavr.collection.Map<String, Integer> map = fixture.create(new InstanceOf<io.vavr.collection.Map<String, Integer>>() {
    });
    assertThat(map.size(), is(3));
    assertThat(map.keySet(), not(hasItem(nullValue())));
    assertThat(map.values(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(map.toJavaMap().values());
    assertContainsOnlyStrings(map.toJavaMap().keySet());
  }

  @Test
  public void shouldGenerateImmutableSets() {

    io.vavr.collection.Set<Integer> set = fixture.create(new InstanceOf<io.vavr.collection.Set<Integer>>() {
    });
    assertThat(set.size(), is(3));
    assertThat(set.toJavaList(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(set.toJavaList());
  }

  @Test
  public void shouldGenerateImmutableSortedMaps() {

    io.vavr.collection.SortedMap<String, Integer> sortedMap = fixture.create(new InstanceOf<io.vavr.collection.SortedMap<String, Integer>>() {
    });
    assertThat(sortedMap.size(), is(3));
    assertThat(sortedMap.keySet(), not(hasItem(nullValue())));
    assertThat(sortedMap.values(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(sortedMap.toJavaMap().values());
    assertContainsOnlyStrings(sortedMap.toJavaMap().keySet());
  }

  @Test
  public void shouldGenerateImmutableSortedSets() {

    io.vavr.collection.SortedSet<Integer> sortedSet = fixture.create(new InstanceOf<io.vavr.collection.SortedSet<Integer>>() {
    });
    assertThat(sortedSet.size(), is(3));
    assertThat(sortedSet.toJavaList(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(sortedSet.toJavaList());
  }


  @Test
  public void shouldGenerateImmutableQueues() {

    io.vavr.collection.Queue<Integer> queue = fixture.create(new InstanceOf<io.vavr.collection.Queue<Integer>>() {
    });
    assertThat(queue.size(), is(3));
    assertThat(queue.toJavaList(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(queue.toJavaList());
  }

  @Test
  public void shouldGenerateImmutableEithers() {
    Either<String, Long> either1 = fixture.create(new InstanceOf<Either<String, Long>>() {
    });
    Either<String, Long> either2 = fixture.create(new InstanceOf<Either<String, Long>>() {
    });
    assertThat(either1.isRight(), is(either2.isLeft()));
    assertThat(either1.isLeft(), is(either2.isRight()));
    assertThat(either1.get(), is(not(nullValue())));
    assertThat(either1.get(), instanceOf(longValue()));
    assertThat(either2.getLeft(), is(not(nullValue())));
    assertThat(either2.getLeft(), instanceOf(string()));
  }

  @Test
  public void shouldGenerateImmutableValidations() {
    Validation<String, Long> validation1 = fixture.create(new InstanceOf<Validation<String, Long>>() {
    });
    Validation<String, Long> validation2 = fixture.create(new InstanceOf<Validation<String, Long>>() {
    });
    assertThat(validation1.isValid(), is(validation2.isInvalid()));
    assertThat(validation1.isInvalid(), is(validation2.isValid()));
    assertThat(validation1.get(), is(not(nullValue())));
    assertThat(validation1.get(), instanceOf(longValue()));
    assertThat(validation2.getError(), is(not(nullValue())));
    assertThat(validation2.getError(), instanceOf(string()));
  }

  @Test
  public void shouldGenerateImmutableOptions() {
    Option<Integer> option = fixture.create(new InstanceOf<Option<Integer>>() {
    });
    assertThat(option.isDefined(), is(true));
    assertThat(option.get(), is(not(nullValue())));
  }

  @Test
  public void shouldGenerateImmutableLinkedHashMultimap() {
    LinkedHashMultimap<String, Integer> linkedHashMultimap = fixture.create(new InstanceOf<LinkedHashMultimap<String, Integer>>() {
    });
    assertThat(linkedHashMultimap.size(), is(3));
    assertThat(linkedHashMultimap.keySet(), not(hasItem(nullValue())));
    assertThat(linkedHashMultimap.values(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(linkedHashMultimap.values().toJavaList());
    assertContainsOnlyStrings(linkedHashMultimap.keySet().toJavaSet());
  }

  @Test
  public void shouldGenerateImmutableHashMultimaps() {
    HashMultimap<String, Integer> hashMultimap = fixture.create(new InstanceOf<HashMultimap<String, Integer>>() {
    });
    assertThat(hashMultimap.size(), is(3));
    assertThat(hashMultimap.keySet(), not(hasItem(nullValue())));
    assertThat(hashMultimap.values(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(hashMultimap.values().toJavaList());
    assertContainsOnlyStrings(hashMultimap.keySet().toJavaSet());
  }

  @Test
  public void shouldGenerateImmutableMultimaps() {
    Multimap<String, Integer> multimap = fixture.create(new InstanceOf<Multimap<String, Integer>>() {
    });
    assertThat(multimap.size(), is(3));
    assertThat(multimap.keySet(), not(hasItem(nullValue())));
    assertThat(multimap.values(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(multimap.values().toJavaList());
    assertContainsOnlyStrings(multimap.keySet().toJavaSet());
  }

  @Test
  public void shouldGenerateImmutableSortedMultimaps() {
    SortedMultimap<String, Integer> sortedMultimap = fixture.create(new InstanceOf<SortedMultimap<String, Integer>>() {
    });
    assertThat(sortedMultimap.size(), is(3));
    assertThat(sortedMultimap.keySet(), not(hasItem(nullValue())));
    assertThat(sortedMultimap.values(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(sortedMultimap.values().toJavaList());
    assertContainsOnlyStrings(sortedMultimap.keySet().toJavaSet());
  }

  @Test
  public void shouldGenerateImmutableTreeMultimaps() {
    TreeMultimap<String, Integer> treeMultimap = fixture.create(new InstanceOf<TreeMultimap<String, Integer>>() {
    });
    assertThat(treeMultimap.size(), is(3));
    assertThat(treeMultimap.keySet(), not(hasItem(nullValue())));
    assertThat(treeMultimap.values(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(treeMultimap.values().toJavaList());
    assertContainsOnlyStrings(treeMultimap.keySet().toJavaSet());
  }

  @Test
  public void shouldGenerateImmutableCharSeqs() {
    CharSeq charSeq = fixture.create(new InstanceOf<CharSeq>() {
    });
    assertThat(charSeq.size(), is(3));
    assertThat(charSeq.toJavaList(), not(hasItem(nullValue())));
    assertContainsOnly(charSeq.toJavaList(), Character.class);
  }

  @Test
  public void shouldGenerateImmutableTrees() {
    Tree<Integer> tree = fixture.create(new InstanceOf<Tree<Integer>>() {
    });
    assertThat(tree.getValue(), is(not(nullValue())));
    assertThat(tree.getValue(), is(instanceOf(Integer.class)));
    assertThat(tree.getChildren().toJavaList(), hasSize(0));
  }

  @Test
  public void shouldGenerateImmutableIterators() {
    java.util.List<Integer> listFromIterator = fixture.create(new InstanceOf<Iterator<Integer>>() {
    }).toJavaList();
    assertThat(listFromIterator.size(), is(3));
    assertThat(listFromIterator, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(listFromIterator);
  }

  @Test
  public void shouldGenerateImmutablePriorityQueues() {
    PriorityQueue<Integer> priorityQueue = fixture.create(new InstanceOf<PriorityQueue<Integer>>() {
    });
    assertThat(priorityQueue.size(), is(3));
    assertThat(priorityQueue.toJavaList(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(priorityQueue.toJavaList());
  }

  @Test
  public void shouldGenerateImmutableLinkedHashSets() {
    LinkedHashSet<Integer> linkedHashSet = fixture.create(new InstanceOf<LinkedHashSet<Integer>>() {
    });
    assertThat(linkedHashSet.size(), is(3));
    assertThat(linkedHashSet.toJavaList(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(linkedHashSet.toJavaList());
  }

  @Test
  public void shouldGenerateImmutableVectors() {
    Vector<Integer> intVector = fixture.create(new InstanceOf<Vector<Integer>>() {
    });
    assertThat(intVector.size(), is(3));
    assertThat(intVector.toJavaList(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(intVector.toJavaList());
  }

  @Test
  public void shouldGenerateImmutableLinkedHashMaps() {
    LinkedHashMap<String, Integer> linkedHashMap = fixture.create(new InstanceOf<LinkedHashMap<String, Integer>>() {
    });
    assertThat(linkedHashMap.size(), is(3));
    assertThat(linkedHashMap.keySet(), not(hasItem(nullValue())));
    assertThat(linkedHashMap.values(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(linkedHashMap.toJavaMap().values());
    assertContainsOnlyStrings(linkedHashMap.toJavaMap().keySet());
  }

  @Test
  public void shouldGenerateImmutableTreeMaps() {
    TreeMap<String, Integer> treeMap = fixture.create(new InstanceOf<TreeMap<String, Integer>>() {
    });
    assertThat(treeMap.size(), is(3));
    assertThat(treeMap.keySet(), not(hasItem(nullValue())));
    assertThat(treeMap.values(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(treeMap.toJavaMap().values());
    assertContainsOnlyStrings(treeMap.toJavaMap().keySet());
  }

  @Test
  public void shouldGenerateImmutableTreeSets() {
    TreeSet<Integer> treeSet = fixture.create(new InstanceOf<TreeSet<Integer>>() {
    });
    assertThat(treeSet.size(), is(3));
    assertThat(treeSet.toJavaList(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(treeSet.toJavaList());
  }

  @Test
  public void shouldGenerateImmutableHashSets() {
    HashSet<Integer> hashSet = fixture.create(new InstanceOf<HashSet<Integer>>() {
    });
    assertThat(hashSet.size(), is(3));
    assertThat(hashSet.toJavaList(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(hashSet.toJavaList());
  }

  @Test
  public void shouldGenerateImmutableArrays() {
    Array<Integer> integerArray = fixture.create(new InstanceOf<Array<Integer>>() {
    });
    assertThat(integerArray.size(), is(3));
    assertThat(integerArray.toJavaList(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(integerArray.toJavaList());
  }

  @Test
  public void shouldGenerateImmutableHashMaps() {
    HashMap<String, Integer> hashMap = fixture.create(new InstanceOf<HashMap<String, Integer>>() {
    });
    assertThat(hashMap.size(), is(3));
    assertThat(hashMap.keySet(), not(hasItem(nullValue())));
    assertThat(hashMap.values(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(hashMap.toJavaMap().values());
    assertContainsOnlyStrings(hashMap.toJavaMap().keySet());
  }

  @Test
  public void shouldGenerateImmutableOrdered() {
    TreeSet<Integer> ordered = (TreeSet<Integer>) fixture.create(new InstanceOf<Ordered<Integer>>() {
    });
    assertThat(ordered.size(), is(3));
    assertThat(ordered.toJavaList(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(ordered.toJavaList());
  }

  @Test
  public void shouldGenerateImmutableIndexedSeqs() {
    IndexedSeq<Integer> indexedSeq = fixture.create(new InstanceOf<IndexedSeq<Integer>>() {
    });
    assertThat(indexedSeq.size(), is(3));
    assertThat(indexedSeq.toJavaList(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(indexedSeq.toJavaList());
  }

  @Test
  public void shouldGenerateImmutableLinearSeqs() {
    LinearSeq<Integer> linearSeq = fixture.create(new InstanceOf<LinearSeq<Integer>>() {
    });
    assertThat(linearSeq.size(), is(3));
    assertThat(linearSeq.toJavaList(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(linearSeq.toJavaList());
  }

  @Test
  public void shouldGenerateImmutableSeqs() {
    Seq<Integer> seq = fixture.create(new InstanceOf<Seq<Integer>>() {
    });
    assertThat(seq.size(), is(3));
    assertThat(seq.toJavaList(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(seq.toJavaList());
  }

  @Test
  public void shouldGenerateImmutableTraversables() {
    Traversable<Integer> traversable = fixture.create(new InstanceOf<Traversable<Integer>>() {
    });
    assertThat(traversable.size(), is(3));
    assertThat(traversable.toJavaList(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(traversable.toJavaList());
  }

  @Test
  public void shouldGenerateImmutableLists2() {
    val list2 = fixture.create(new InstanceOf<List<Integer>>() {});
    assertThat(list2.size(), is(3));
    assertThat(list2, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list2.toJavaList());
  }

  @Test
  public void shouldGenerateImmutableListsUsingClassSignature() {
    final List<Integer> list = AnyVavr.listOf(intValues());
    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list.toJavaList());
  }

}
