package pmml;

import org.dmg.pmml.FieldName;
import org.dmg.pmml.PMML;
import org.jpmml.evaluator.*;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/27 8:00 PM,
 */
public class PMMLDemo{
	private Evaluator loadPmml() {
		PMML pmml = new PMML();
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream("/Users/tony/Documents/private/data_mining/ltr/model/xgboost.pmml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (inputStream == null) {
			return null;
		}
		InputStream is = inputStream;
		try {
			pmml = org.jpmml.model.PMMLUtil.unmarshal(is);
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (JAXBException e1) {
			e1.printStackTrace();
		} finally {
			//关闭输入流
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ModelEvaluatorFactory modelEvaluatorFactory = ModelEvaluatorFactory.newInstance();
		Evaluator evaluator = modelEvaluatorFactory.newModelEvaluator(pmml);
		return evaluator;
	}

	private int predict(Evaluator evaluator,Map<String, Double> featuremap) {

		List<InputField> inputFields = evaluator.getInputFields();
		Map<FieldName, FieldValue> arguments = new LinkedHashMap<>();
		for (InputField inputField : inputFields) {
			FieldName inputFieldName = inputField.getName();
			Object rawValue = featuremap.get(inputFieldName.getValue());
			FieldValue inputFieldValue = inputField.prepare(rawValue);
			arguments.put(inputFieldName, inputFieldValue);
		}

		Map<FieldName, ?> results = evaluator.evaluate(arguments);
		List<TargetField> targetFields = evaluator.getTargetFields();

		TargetField targetField = targetFields.get(0);
		FieldName targetFieldName = targetField.getName();

		Object targetFieldValue = results.get(targetFieldName);
		System.out.println("target: " + targetFieldName.getValue() + " value: " + targetFieldValue);
		int primitiveValue = -1;
		if (targetFieldValue instanceof Computable) {
			Computable computable = (Computable) targetFieldValue;
			primitiveValue = (int)computable.getResult();
		}
		return primitiveValue;
	}

	public static void main(String args[]){
		PMMLDemo demo = new PMMLDemo();
		Evaluator model = demo.loadPmml();
		Map<String, Double> data = new HashMap<>();
		//这里的key一定要对应python中的列名，一开始我在网上找的例子是随便起的名字，不管输入什么数据返回结果都是0
		data.put("x1",17.19);
		data.put("x2",22.07);
		data.put("x3",111.6);
		data.put("x4",928.3);
		data.put("x5",0.09726);
		data.put("x6",0.08995);
		data.put("x7",0.09061);
		data.put("x8",0.06527);
		data.put("x9",0.1867);
		data.put("x10",0.0558);
		data.put("x11",0.4203);
		data.put("x12",0.7383);
		data.put("x13",2.819);
		data.put("x14",45.42);
		data.put("x15",0.004493);
		data.put("x16",0.01206);
		data.put("x17",0.02048);
		data.put("x18",0.009875);
		data.put("x19",0.01144);
		data.put("x20",0.001575);
		data.put("x21",21.58);
		data.put("x22",29.33);
		data.put("x23",140.5);
		data.put("x24",1436.0);
		data.put("x25",0.1558);
		data.put("x26",0.2567);
		data.put("x27",0.3889);
		data.put("x28",0.1984);
		data.put("x29",0.3216);
		data.put("x30",0.0757);
		long start = System.currentTimeMillis();
		for(int i=1; i<50; i++){
			demo.predict(model,data);
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}


}
