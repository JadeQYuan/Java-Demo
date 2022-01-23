package plus.knowing.distribute.corba;

/**
* plus/knowing/distribute/corba/DTOHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从ServerService.idl
* 2022年1月23日 星期日 上午10时16分48秒 CST
*/

public final class DTOHolder implements org.omg.CORBA.portable.Streamable
{
  public plus.knowing.distribute.corba.DTO value = null;

  public DTOHolder ()
  {
  }

  public DTOHolder (plus.knowing.distribute.corba.DTO initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = plus.knowing.distribute.corba.DTOHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    plus.knowing.distribute.corba.DTOHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return plus.knowing.distribute.corba.DTOHelper.type ();
  }

}
