package plus.knowing.distribute.corba;


/**
* plus/knowing/distribute/corba/ServerServicePOA.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从ServerService.idl
* 2022年1月23日 星期日 上午10时16分19秒 CST
*/

public abstract class ServerServicePOA extends org.omg.PortableServer.Servant
 implements plus.knowing.distribute.corba.ServerServiceOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("method1", new java.lang.Integer (0));
    _methods.put ("method2", new java.lang.Integer (1));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // corba/ServerService/method1
       {
         String $result = null;
         $result = this.method1 ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // corba/ServerService/method2
       {
         String str = in.read_string ();
         plus.knowing.distribute.corba.DTO $result = null;
         $result = this.method2 (str);
         out = $rh.createReply();
         plus.knowing.distribute.corba.DTOHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:corba/ServerService:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public ServerService _this() 
  {
    return ServerServiceHelper.narrow(
    super._this_object());
  }

  public ServerService _this(org.omg.CORBA.ORB orb) 
  {
    return ServerServiceHelper.narrow(
    super._this_object(orb));
  }


} // class ServerServicePOA
