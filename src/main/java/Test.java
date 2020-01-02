import ml.dmlc.xgboost4j.java.XGBoostError;

import java.util.UUID;

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/26 8:48 PM,
 */
public class Test{

	public static void main(String[] args) throws XGBoostError{
		while(true){
			System.out.println(UUID.randomUUID().toString().substring(0,8));
		}
	}
}
