package com.example.parsingjsonandxml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ViewDataActivity extends AppCompatActivity {
    TextView XmlContentTextView, JsonContentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        XmlContentTextView=(TextView) findViewById(R.id.view_xml_content_id);
        JsonContentTextView=(TextView) findViewById(R.id.view_json_content_id);
        Intent intent=getIntent();
        String datatype=intent.getStringExtra("datatype");
        if(datatype.equals("xml"))
        {
            XmlContentTextView.setText("");
            //XmlContentTextView.setText("Test XML parsed content");
            try {
                InputStream is = getAssets().open("weather.xml");

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(is);

                Element element = (Element) doc.getDocumentElement();
                element.normalize();

                NodeList nList = doc.getElementsByTagName("weather");

                for (int i = 0; i < nList.getLength(); i++) {

                    Node node = nList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element2 = (Element) node;
                        XmlContentTextView.setText(XmlContentTextView.getText() + "\nCity Name : " + getValue("City_Name", element2) + "\n");
                        XmlContentTextView.setText(XmlContentTextView.getText() + "Latitude : " + getValue("Latitude", element2) + "\n");
                        XmlContentTextView.setText(XmlContentTextView.getText() + "Longitude : " + getValue("Longitude", element2) + "\n");
                        XmlContentTextView.setText(XmlContentTextView.getText() + "Temperature : " + getValue("Temperature", element2) + "\n");
                        XmlContentTextView.setText(XmlContentTextView.getText() + "Humidity : " + getValue("Humidity", element2) + "\n");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(datatype.equals("json"))
        {

            parseJSON();


        }

    }

    private void parseJSON() {
        try {
            InputStream inputStream=getAssets().open("weather.json");

                byte[] data=new byte[inputStream.available()];
                inputStream.read(data);
                String jsonString =new String(data);
                JSONObject jsonObject=new JSONObject(jsonString);
                JSONObject weather=jsonObject.getJSONObject("weather");
                JsonContentTextView.append("City Name:"+weather.getString("City_Name")+"\n");
                JsonContentTextView.append("Latitude:"+weather.getString("Latitude")+"\n");
                JsonContentTextView.append("Longitude:"+weather.getString("Longitude")+"\n");
                JsonContentTextView.append("Temperature:"+weather.getString("Temperature")+"\n");
                JsonContentTextView.append("Humidity:"+weather.getString("Humidity")+"\n");


        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

}