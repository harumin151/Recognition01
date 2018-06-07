package recognition01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectFacesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;

public class Recognition01_main {

	public static void main(String[] args){

	VisualRecognition service = new VisualRecognition("2018-03-19");
	service.setApiKey("J16015");

	DetectFacesOptions detectFacesOptions = null;
	try {
		detectFacesOptions = new DetectFacesOptions.Builder()
		  .imagesFile(new File("./img/obama.jpg"))
		  .build();
	} catch (FileNotFoundException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
	DetectedFaces result = service.detectFaces(detectFacesOptions).execute();
	System.out.println(result);


	//　ココから第２回授業だお↓
	String s = String.valueOf(result);
	ObjectMapper mapper = new ObjectMapper();
	JsonNode node = null;
	try {
		node = mapper.readTree(s);
	} catch (IOException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}

	//age_minを取得する
	int age_min =
			node.get("images").get(0).get("faces").get(0)
.get("age").get("min").asInt();
	System.out.println("age_min:" + age_min);

	//age_maxを取得する
	int age_max =
			node.get("images").get(0).get("faces").get(0)
			.get("age").get("max").asInt();
	System.out.println("age_max:" + age_max);

	//age_scoreを取得する
	double age_score =
			node.get("images").get(0).get("faces").get(0)
			.get("age").get("score").asInt();
	System.out.println("age_score:" + age_score);

	//Genderを取得する
	int gender =
			node.get("images").get(0).get("faces").get(0)
			.get("gender").get("gender").asInt();
	System.out.println("Gender:" + gender);

	//Gender_scoreを取得する
	double gender_score =
			node.get("images").get(0).get("faces").get(0)
			.get("gender").get("score").asInt();
	System.out.println("Gender_score:" + gender_score);



	MySQL mysql = new MySQL();

	mysql.updateImage(age_min, age_max,age_score,gender,gender_score);



	}
}
