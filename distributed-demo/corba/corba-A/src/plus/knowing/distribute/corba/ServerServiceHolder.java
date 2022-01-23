package plus.knowing.distribute.corba;

/**
* plus/knowing/distribute/corba/ServerServiceHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从ServerService.idl
* 2022年1月23日 星期日 上午10时16分48秒 CST
*/

public final class ServerServiceHolder implements org.omg.CORBA.portable.Streamable
{
  public plus.knowing.distribute.corba.ServerService value = null;

  public ServerServiceHolder ()
  {
  }

  public ServerServiceHolder (plus.knowing.distribute.corba.ServerService initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = plus.knowing.distribute.corba.ServerServiceHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    plus.knowing.distribute.corba.ServerServiceHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return plus.knowing.distribute.corba.ServerServiceHelper.type ();
  }

}
