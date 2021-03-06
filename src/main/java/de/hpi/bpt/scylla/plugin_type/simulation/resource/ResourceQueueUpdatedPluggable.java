package de.hpi.bpt.scylla.plugin_type.simulation.resource;

import java.util.Iterator;
import java.util.Set;

import de.hpi.bpt.scylla.plugin_loader.PluginLoader;
import de.hpi.bpt.scylla.plugin_type.IPluggable;
import de.hpi.bpt.scylla.simulation.event.ScyllaEvent;

public abstract class ResourceQueueUpdatedPluggable implements IPluggable{

    public static ScyllaEvent runPlugins(Set<String> resourceQueuesUpdated) {
        Class<ResourceQueueUpdatedPluggable> clazz = ResourceQueueUpdatedPluggable.class;
        Iterator<? extends ResourceQueueUpdatedPluggable> plugins = PluginLoader.dGetPlugins(clazz);
        while (plugins.hasNext()) {
        	ResourceQueueUpdatedPluggable plugin = plugins.next();
        	ScyllaEvent eventForPlugin = plugin.eventToBeScheduled(resourceQueuesUpdated);
        	if(eventForPlugin != null)return eventForPlugin;
        }
        return null;
    }
    
    public abstract ScyllaEvent eventToBeScheduled(Set<String> resourceQueuesUpdated);

}
