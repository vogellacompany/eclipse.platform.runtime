package org.eclipse.core.runtime;

/*
 * (c) Copyright IBM Corp. 2000, 2001.
 * All Rights Reserved.
 */

/**
 * An abstract superclass implementing the <code>IAdaptable</code>
 * interface. <code>getAdapter</code> invocations are directed
 * to the platform's adapter manager.
 * <p>
 * Note: In situations where it would be awkward to subclass this
 * class, the same affect can be achieved simply by implementing
 * the <code>IAdaptable</code> interface and explicitly forwarding
 * the <code>getAdapter</code> request to the platform's 
 * adapater manager. The method would look like:
 * <pre>
 *     public Object getAdapter(Class adapter) {
 *         return Platform.getAdapterManager().getAdapter(this, adapter);
 *     }
 * </pre>
 * </p>
 * <p>
 * Clients may subclass.
 * </p>
 *
 * @see Platform#getAdapterManager
 */
public abstract class PlatformObject implements IAdaptable {
/**
 * Constructs a new platform object.
 */
public PlatformObject() {
}
/**
 * Returns an object which is an instance of the given class
 * associated with this object. Returns <code>null</code> if
 * no such object can be found.
 * <p>
 * This implementation of the method declared by <code>IAdaptable</code>
 * passes the request along to the platform's adapter manager; roughly
 * <code>Platform.getAdapterManager().getAdapter(this, adapter)</code>.
 * Subclasses may override this method (however, if they do so, they
 * should invoke the method on their superclass to ensure that the
 * Platform's adapter manager is consulted).
 * </p>
 *
 * @see IAdaptable#getAdapter
 * @see Platform#getAdapterManager
 */
public Object getAdapter(Class adapter) {
	return Platform.getAdapterManager().getAdapter(this, adapter);
}
}
