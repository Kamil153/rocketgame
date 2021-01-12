package dev.kamilpolak.rocketgame.ecs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EntityConstraint {
    Set<Class<? extends Component>> include;
    Set<Class<? extends Component>> exclude;

    public EntityConstraint(Class<? extends Component>[] include, Class<? extends Component>[] exclude) {
        this.include = new HashSet<>(Arrays.asList(include));
        this.exclude = new HashSet<>(Arrays.asList(exclude));
    }

    public boolean check(Entity entity) {
        for(var component: include) {
            if(!entity.hasComponent(component)) {
                return false;
            }
        }
        for(var component: exclude) {
            if(entity.hasComponent(component)) {
                return false;
            }
        }
        return true;
    }
}
