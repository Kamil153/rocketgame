package dev.kamilpolak.rocketgame.ecs;

import org.junit.Assert;
import org.junit.Test;

public class QueryTest {
    static class TestComponent1 implements IComponent {}
    static class TestComponent2 implements IComponent {}

    @Test
    public void checkTest() {
        Query query1 = new Query().include(TestComponent1.class);
        Query query2 = new Query().exclude(TestComponent2.class);
        Query query3 = new Query().include(TestComponent1.class).exclude(TestComponent2.class);

        Entity entity1 = new Entity();
        entity1.addComponent(new TestComponent1());

        Entity entity2 = new Entity();
        entity2.addComponent(new TestComponent1());
        entity2.addComponent(new TestComponent2());

        Entity entity3 = new Entity();

        Assert.assertTrue(query1.check(entity1));
        Assert.assertTrue(query1.check(entity2));
        Assert.assertFalse(query1.check(entity3));
        Assert.assertTrue(query2.check(entity1));
        Assert.assertFalse(query2.check(entity2));
        Assert.assertTrue(query2.check(entity3));
        Assert.assertTrue(query3.check(entity1));
        Assert.assertFalse(query3.check(entity2));
        Assert.assertFalse(query3.check(entity3));
    }
}
