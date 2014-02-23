JFixture
========

An attempt to reimplement core features of a popular .NET anonymous value generator in Java

Creating a fixture object
-

New fixture:

    Fixture fixture = new Fixture();


Creating instances
-

A value of non-generic class:

    int number = fixture.create(int.class);
    String text = fixture.create(String.class);
    
A value of generic class (including collections):

    ArrayList<Integer> list = f.create(new InstanceOf<ArrayList<Integer>>() {});

