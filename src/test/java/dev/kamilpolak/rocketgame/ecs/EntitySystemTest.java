package dev.kamilpolak.rocketgame.ecs;

import org.junit.Assert;
import org.junit.Test;

public class EntitySystemTest {

    static class TestSystem extends EntitySystem {
        public TestSystem(int priority) { super(priority); }

        @Override
        void update(float deltaTime) { throw new RuntimeException(); }
    }

    @Test
    public void compareToTest() {
        EntitySystem system1 = new TestSystem(5);
        EntitySystem system2 = new TestSystem(10);
        EntitySystem system3 = new TestSystem(10);
        Assert.assertEquals(1, system1.compareTo(system2));
        Assert.assertEquals(0, system2.compareTo(system3));
        Assert.assertEquals(-1, system2.compareTo(system1));
    }
}
