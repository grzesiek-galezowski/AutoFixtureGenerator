JFixture
========

An attempt to reimplement core features of a popular .NET anonymous value generator in Java

Creating a fixture object
-

New fixture object:

    Fixture fixture = new Fixture();


Creating instances
-

A value of non-generic class:

    int number = fixture.create(int.class);
    String text = fixture.create(String.class);
    
A value of generic class (including collections):

    ArrayList<Integer> list = fixture.create(new InstanceOf<ArrayList<Integer>>() {});

Fixture customization
-

Customizations always take precedence over built-in generators.

Example of new integer generation customization that always returns 12:

    fixture.register(new InstanceGenerator() {
      @Override
      public <T> boolean AppliesTo(TypeToken<T> arg) {
        return arg.getRawType() == int.class;
      }
      
      @Override
      public <T> T next(TypeToken<T> arg0, Fixture arg1) {
        //note the cast to T:
        return (T) Integer.valueOf(12);
      }
    });

Clearing all customizations:

    f.clearCustomizations();


