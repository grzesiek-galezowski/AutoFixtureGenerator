AutoFixtureGenerator 
========

[![Build Status](https://travis-ci.org/grzesiek-galezowski/AutoFixtureGenerator.svg?branch=master)](https://travis-ci.org/grzesiek-galezowski/AutoFixtureGenerator) [![Dependency Status](https://www.versioneye.com/java/com.github.autofixture:autofixturegenerator/1.0.0/badge.svg)](https://www.versioneye.com/java/com.github.autofixture:autofixturegenerator/1.0.0) [![Inline docs](http://inch-ci.org/github/grzesiek-galezowski/AutoFixtureGenerator.svg?branch=master)](http://inch-ci.org/github/grzesiek-galezowski/AutoFixtureGenerator)
![](https://reposs.herokuapp.com/?path=grzesiek-galezowski/AutoFixtureGenerator&style=flat)
[![License](http://img.shields.io/:license-mit-blue.svg)](http://doge.mit-license.org)

An attempt to reimplement core features of a popular .NET anonymous value generator - AutoFixture - in Java

Maven pom
-

Maven xml pom can be obtained at: http://mvnrepository.com/artifact/com.github.autofixture/autofixturegenerator

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

    f.register(new InstanceGenerator() {
    
      @Override
      public <T> boolean appliesTo(InstanceType<T> arg0) {
        return arg0.getRawType() == int.class;
      }
    
      @SuppressWarnings("unchecked")
      @Override
      public <T> T next(InstanceType<T> arg0, FixtureContract arg1) {
        return (T) Integer.valueOf(12);
      }

      @Override
      void setOmittingAutoProperties(boolean isOn) {}
    });


Clearing all customizations:

    fixture.clearCustomizations();


Any method helpers
-

Starting with version 0.3.0, new "any" method helpers are available. two simple examples:

    import static autofixture.publicinterface.Generate.*;
    
    public class AnyGenerationMethodsSpecification {
      @Test
      public void shouldGenerateEachTimeDifferentString() {
        String str1 = anyString();
        String str2 = anyString();
    
        assertThat(str1, is(not(str2)));
      }
	  
      @Test
      public void shouldGenerateEachTimeDifferentInstance() {
        GenericObject<Integer> o1 = any(new InstanceOf<GenericObject<Integer>>() {});
        GenericObject<Integer> o2 = any(new InstanceOf<GenericObject<Integer>>() {});
      
        assertThat(o1, is(not(o2)));
      }
}


