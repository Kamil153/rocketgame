package dev.kamilpolak.rocketgame.ecs;

import org.junit.Assert;
import org.junit.Test;

public class EntitySystemTest {
    static class TestException extends RuntimeException {
        private final float deltaTime;
        public TestException(float deltaTime) { this.deltaTime = deltaTime; }
        public float getDeltaTime() { return deltaTime; }
    }

    static class TestSystem extends EntitySystem {
        public TestSystem(int priority) { super(priority); }

        @Override
        void update(float deltaTime) { throw new TestException(deltaTime); }
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

    @Test
    public void updateTest() {
        EntitySystem system = new TestSystem(10);
        int deltaTime = 10;
        TestException ex = Assert.assertThrows(TestException.class, () -> system.runUpdate(deltaTime));
        Assert.assertEquals(deltaTime, ex.getDeltaTime(), 1);
    }

    @Test
    public void getPriorityTest() {
        int priority = 10;
        EntitySystem system = new TestSystem(priority);
        Assert.assertEquals(priority, system.getPriority());
    }
}
