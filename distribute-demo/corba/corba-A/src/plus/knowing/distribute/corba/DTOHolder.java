package plus.knowing.distribute.corba;

/**
* plus/knowing/distribute/corba/DTOHolder.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��ServerService.idl
* 2022��1��23�� ������ ����10ʱ16��48�� CST
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
