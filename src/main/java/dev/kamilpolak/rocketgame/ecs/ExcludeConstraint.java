package dev.kamilpolak.rocketgame.ecs;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ExcludeConstraint implements IEntityConstraint {
    Set<Class<? extends Component>> exclude;

    public ExcludeConstraint(Collection<Class<? extends Component>> exclude) {
        this.exclude = new HashSet<>(exclude);
    }

    public boolean check(Entity entity) {
        for(Class<? extends Component> component: exclude) {
            if(entity.hasComponent(component)) {
                return false;
            }
        }
        return true;
    }
}