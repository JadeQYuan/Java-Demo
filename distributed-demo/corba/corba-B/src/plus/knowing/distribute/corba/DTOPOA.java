package plus.knowing.distribute.corba;


/**
* plus/knowing/distribute/corba/DTOPOA.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从ServerService.idl
* 2022年1月23日 星期日 上午10时16分19秒 CST
*/

public abstract class DTOPOA extends org.omg.PortableServer.Servant
 implements plus.knowing.distribute.corba.DTOOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("_get_name", new java.lang.Integer (0));
    _methods.put ("_set_name", new java.lang.Integer (1));
    _methods.put ("_get_count", new java.lang.Integer (2));
    _methods.put ("_set_count", new java.lang.Integer (3));
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
       case 0:  // corba/DTO/_get_name
       {
         String $result = null;
         $result = this.name ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // corba/DTO/_set_name
       {
         String newName = in.read_string ();
         this.name (newName);
         out = $rh.createReply();
         break;
       }

       case 2:  // corba/DTO/_get_count
       {
         int $result = (int)0;
         $result = this.count ();
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 3:  // corba/DTO/_set_count
       {
         int newCount = in.read_long ();
         this.count (newCount);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:corba/DTO:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public DTO _this() 
  {
    return DTOHelper.narrow(
    super._this_object());
  }

  public DTO _this(org.omg.CORBA.ORB orb) 
  {
    return DTOHelper.narrow(
    super._this_object(orb));
  }


} // class DTOPOA
