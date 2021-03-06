package plus.knowing.distribute.corba;


/**
* plus/knowing/distribute/corba/DTOHelper.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从ServerService.idl
* 2022年1月23日 星期日 上午10时16分48秒 CST
*/

abstract public class DTOHelper
{
  private static String  _id = "IDL:corba/DTO:1.0";

  public static void insert (org.omg.CORBA.Any a, plus.knowing.distribute.corba.DTO that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static plus.knowing.distribute.corba.DTO extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (plus.knowing.distribute.corba.DTOHelper.id (), "DTO");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static plus.knowing.distribute.corba.DTO read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_DTOStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, plus.knowing.distribute.corba.DTO value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static plus.knowing.distribute.corba.DTO narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof plus.knowing.distribute.corba.DTO)
      return (plus.knowing.distribute.corba.DTO)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      plus.knowing.distribute.corba._DTOStub stub = new plus.knowing.distribute.corba._DTOStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static plus.knowing.distribute.corba.DTO unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof plus.knowing.distribute.corba.DTO)
      return (plus.knowing.distribute.corba.DTO)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      plus.knowing.distribute.corba._DTOStub stub = new plus.knowing.distribute.corba._DTOStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
