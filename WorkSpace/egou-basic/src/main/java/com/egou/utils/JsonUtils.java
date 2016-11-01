package com.egou.utils;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.mapper.MapperException;
import com.sdicons.json.model.JSONArray;
import com.sdicons.json.model.JSONValue;
import com.sdicons.json.parser.JSONParser;




public class JsonUtils {
	private static Gson gson = null;
	static {
		if (gson == null) {
			gson = new Gson();
		}
	}
	/**
	 * jsonת��List���Ͷ��� ���Թ��˲���Ҫ������
	 * 
	 * @param jsonStr
	 * @param cla
	 * @return
	 */
	public static Object json2Objectlist(String jsonStr, Class<?> cla) {
		List<Object> list = new ArrayList<Object>();
		try {
			List<Map<String, Object>> maplist = json2MapList(jsonStr);
			if (maplist != null && maplist.size() > 0) {
				for (Map<String, Object> map2 : maplist) {
					Object obj = cla.newInstance();
					org.apache.commons.beanutils.BeanUtils.populate(obj, map2);
					list.add(jsonStrToObject(objectToJson(obj), cla));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	/**
	 * jsonת���� List<map<String,Object>>
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static List<Map<String, Object>> json2MapList(String jsonStr) {
		net.sf.json.JSONArray jsonArr = net.sf.json.JSONArray.fromObject(jsonStr);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Iterator<JSONObject> it = jsonArr.iterator();
		while (it.hasNext()) {
			JSONObject json2 = it.next();
			list.add(json2Map(json2.toString()));
		}
		return list;
	}

	/**
	 * jsonװ���� Map<String,Object>
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Map<String, Object> json2Map(String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		// ��������
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			// ����ڲ㻹������Ļ�����������
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<net.sf.json.JSONArray> it = ((net.sf.json.JSONArray) v).iterator();
				while (it.hasNext()) {
					net.sf.json.JSONArray json2 = it.next();
					list.add(json2Map(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}

	/**
	 * JSON�ַ���ת����JAVA����
	 * 
	 * @param jsonStr
	 * @param cla
	 * @return
	 * @throws MapperException
	 * @throws TokenStreamException
	 * @throws RecognitionException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object jsonStrToObject(String jsonStr, Class<?> cla) {
		Object obj = null;
		try {
			JSONParser parser = new JSONParser(new StringReader(jsonStr));
			JSONValue jsonValue = parser.nextValue();
			if (jsonValue instanceof com.sdicons.json.model.JSONArray) {
				List list = new ArrayList();
				JSONArray jsonArray = (JSONArray) jsonValue;
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONValue jsonObj = jsonArray.get(i);
					try {
						Object javaObj = JSONMapper.toJava(jsonObj, cla);
						list.add(javaObj);
					} catch (Exception ex) {
						continue;
					}

				}
				obj = list;
			} else if (jsonValue instanceof com.sdicons.json.model.JSONObject) {
				obj = JSONMapper.toJava(jsonValue, cla);
			} else {
				obj = jsonValue;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 
	 * @Title: objectToJson
	 * @Description: ������ת����json��ʽ
	 * @param @param ts
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String objectToJson(Object ts) {
		String jsonStr = null;
		if (gson != null) {
			jsonStr = gson.toJson(ts);
		}
		return jsonStr;
	}
}
