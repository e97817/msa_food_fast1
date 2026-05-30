
package bo.gob.food_fast.common.error;

import java.sql.CallableStatement;
import java.sql.Connection;

public final class Error {
  public static final int VEHICULOS                     = 0;

  public static final int FISCALIZACION                 = 1;

  public static final int INMUEBLES                     = 2;

  public static final int PORTAL                        = 3;

  public static final int ACTIVIDADES_ECONOMICAS        = 4;

  public static final int ENTORNO                       = 5;

  public static final int INMUEBLESFIS                  = 6;

  public static final int GESTION_atencionS              = 7;

  public static final int SOPORTE_SERVICIO              = 8;

  public static final int CONTRIBUYENTES                = 9;

  public static final int CONTRIBUYENTEFIS              = 10;

  public static final int TASAS                         = 11;


  public static final int SERVICIOS                     = 13;

  // private static final String TIPO_ERROR                = "JAVA";

  protected static final String ERROR_REGISTRO          = " al registrar la excepción";

  protected static final String ERROR_CONEXION          = " al realizar la conexión";


}
