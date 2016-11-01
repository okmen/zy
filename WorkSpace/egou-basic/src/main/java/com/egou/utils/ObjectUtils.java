package com.egou.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;


public class ObjectUtils {
	/**
	 * @Title:isEmpty
	 * @Description:�����Ƿ�Ϊ��
	 * @param @param s
	 * @param @return
	 * @return boolean
	 * @throws
	 * @author xiehz
	 * @date 2015��4��27��
	 */
	public static boolean isEmpty(Collection<?> s) {
		if (null == s) {
			return true;
		}
		return s.isEmpty();
	}

	/**
	 * @Title:isEmpty
	 * @Description:map�Ƿ�Ϊ��
	 * @param @param s
	 * @param @return
	 * @return boolean
	 * @throws
	 * @author xiehz
	 * @date 2015��4��27��
	 */
	public static boolean isEmpty(Map<?, ?> s) {
		if (null == s) {
			return true;
		}
		return s.isEmpty();
	}

	/**
	 * @Title:isEmpty
	 * @Description:�ַ����Ƿ�Ϊ��
	 * @param @param s
	 * @param @return
	 * @return boolean
	 * @throws
	 * @author xiehz
	 * @date 2015��4��27��
	 */
	public static boolean isEmpty(String s) {
		if (null == s) {
			return true;
		}
		return s.toString().trim().length() <= 0;
	}

	/**
	 * @Title:isEmpty
	 * @Description:�����Ƿ�Ϊ��
	 * @param @param s
	 * @param @return
	 * @return boolean
	 * @throws
	 * @author xiehz
	 * @date 2015��4��27��
	 */
	public static <T> boolean isEmpty(T s) {
		if (null == s) {
			return true;
		}
		return false;

	}

	/**
	 * @Title:isEmpty
	 * @Description:�����Ƿ�Ϊ��
	 * @param @param s
	 * @param @return
	 * @return boolean
	 * @throws
	 * @author xiehz
	 * @date 2015��4��27��
	 */
	public static <T> boolean isEmpty(T[] s) {
		if (null == s) {
			return true;
		}
		return Array.getLength(s) < 1;
	}

	/**
	 * @Title:isNotEmpty
	 * @Description:���ϲ�Ϊ��
	 * @param @param s
	 * @param @return
	 * @return boolean
	 * @throws
	 * @author xiehz
	 * @date 2015��4��27��
	 */
	public static boolean isNotEmpty(Collection<?> s) {
		if (null == s) {
			return false;
		}
		return !s.isEmpty();
	}

	/**
	 * @Title:isNotEmpty
	 * @Description:map��Ϊ��
	 * @param @param s
	 * @param @return
	 * @return boolean
	 * @throws
	 * @author xiehz
	 * @date 2015��4��27��
	 */
	public static boolean isNotEmpty(Map<?, ?> s) {
		if (null == s) {
			return false;
		}
		return !s.isEmpty();
	}

	/**
	 * �ַ�����Ϊ�� ***********************************
	 * 
	 * @author xiehz
	 * @create_date 2013-10-11 ����10:03:09
	 * @param t
	 * @return ***********************************
	 */
	public static boolean isNotEmpty(String s) {
		if (null == s) {
			return false;
		}
		return s.toString().trim().length() > 0;
	}

	/**
	 * int ���ڵ���0 ***********************************
	 * 
	 * @author xiehz
	 * @create_date 2013-10-11 ����10:03:09
	 * @param t
	 * @return ***********************************
	 */
	public static boolean isNotEmpty(Integer s) {
		if (null == s) {
			return false;
		}
		return s >= 0;
	}

	/**
	 * �����Ƿ�Ϊ�� ***********************************
	 * 
	 * @author xiehz
	 * @create_date 2013-10-11 ����10:03:09
	 * @param t
	 * @return ***********************************
	 */
	public static <T> boolean isNotEmpty(T s) {
		if (null == s) {
			return false;
		}
		return !isEmpty(s);
	}

	/**
	 * ת��listΪ id�б� ***********************************
	 * 
	 * @author xiehz
	 * @create_date 2013-10-11 ����10:03:18
	 * @param t
	 * @return ***********************************
	 */
	public static <T> String listToString(Collection<T> t, String keyName) {
		String methodName = "";
		StringBuilder keys = new StringBuilder();
		try {
			for (T t2 : t) {
				methodName = "get" + keyName.substring(0, 1).toUpperCase() + keyName.substring(1);
				Method method = t2.getClass().getDeclaredMethod(methodName);
				Object res = method.invoke(t2);
				if (null != res) {
					keys.append(res);
					keys.append(",");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (keys.length() > 0) {
			return keys.substring(0, keys.length() - 1);
		} else {
			return "";
		}
	}

	/**
	 * ת��listΪ id�б� ***********************************
	 * 
	 * @author xiehz
	 * @create_date 2013-10-11 ����10:03:18
	 * @param t
	 * @return ***********************************
	 */
	public static <T> String arrayToString(T[] t) {
		StringBuilder keys = new StringBuilder();
		for (T t2 : t) {
			keys.append(t2);
			keys.append(",");
		}
		if (keys.length() > 0) {
			return keys.substring(0, keys.length() - 1);
		} else {
			return "";
		}
	}

	/**
	 * ת��listΪ id�б� ***********************************
	 * 
	 * @author xiehz
	 * @create_date 2013-10-11 ����10:03:18
	 * @param t
	 * @return ***********************************
	 */
	public static <T> String listToString(Collection<T> t) {
		StringBuilder keys = new StringBuilder();
		for (T t2 : t) {
			keys.append(t2);
			keys.append(",");
		}
		if (keys.length() > 0) {
			return keys.substring(0, keys.length() - 1);
		} else {
			return "";
		}
	}

	public static String toFirstLetterUpperCase(String str) {
		if (str == null || str.length() < 2) {
			return str;
		}
		String firstLetter = str.substring(0, 1).toUpperCase();
		return firstLetter + str.substring(1, str.length());
	}

	/**
	 * ����ת��Ϊ4λ�ֽ�����
	 * 
	 * @author xiehz
	 * @create_date 2015-1-27 ����5:11:58
	 * @param intValue
	 * @return
	 */
	public static byte[] int2Byte(int intValue) {
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			b[i] = (byte) (intValue >> 8 * (3 - i) & 0xFF);
			// System.out.print(Integer.toBinaryString(b[i])+" ");
			// System.out.print((b[i] & 0xFF) + " ");
		}
		return b;
	}

	/**
	 * 4λ�ֽ�����ת��Ϊ����
	 * 
	 * @author xiehz
	 * @create_date 2015-1-27 ����5:11:47
	 * @param b
	 * @return
	 */
	public static int byte2Int(byte[] b) {
		int intValue = 0;
		// int tempValue = 0xFF;
		for (int i = 0; i < b.length; i++) {
			intValue += (b[i] & 0xFF) << (8 * (3 - i));
			// System.out.print(Integer.toBinaryString(intValue)+" ");
		}
		return intValue;
	}

	/**
	 * @author xiehz
	 * @create_date 2014-8-7 ����10:16:59
	 * @param score
	 * @return
	 */
	public static Float parseFloat(String score) {
		if (isNotEmpty(score)) {
			if (isDouble(score)) {
				return Float.valueOf(score);
			}
		}
		return 0f;
	}

	/**
	 * @author xiehz
	 * @create_date 2014-8-7 ����10:16:59
	 * @param score
	 * @return
	 */
	public static Integer parseInt(String score) {
		if (isNotEmpty(score)) {
			if (isDouble(score)) {
				return Integer.valueOf(score);
			}
		}
		return 0;
	}

	public static final Pattern integerPattern = Pattern.compile("^[-\\+]?[\\d]*$");

	/**
	 * 
	 * @author xiehz
	 * @create_date 2014-8-7 ����10:23:15
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		return integerPattern.matcher(str).matches();
	}

	/*
	 * 
	 * @param str
	 * 
	 * @return
	 */
	public static final Pattern floatPattern = Pattern.compile("^[-\\+]?[.\\d]*$");

	/**
	 * �ж��Ƿ�Ϊ������������double��float
	 * 
	 * @author xiehz
	 * @create_date 2014-8-7 ����10:22:54
	 * @param str������ַ���
	 * @return�Ǹ���������true,���򷵻�false
	 */
	public static boolean isDouble(String str) {
		return floatPattern.matcher(str).matches();
	}

	/**
	 * @author xiehz
	 * @create_date 2014-8-8 ����11:26:33
	 * @param difficulty
	 * @return
	 */
	public static byte stringToByte(String difficulty) {

		if (ObjectUtils.isNotEmpty(difficulty)) {
			if (difficulty.length() == 1) {
				return Byte.valueOf(difficulty);
			}
		}
		return (byte) 0;
	}

	/**
	 * @author xiehz
	 * @create_date 2014-9-1 ����5:26:29
	 * @param paperIdSb
	 * @return
	 */
	public static String setToString(Set<Integer> set) {
		if (isEmpty(set)) {
			return "";
		}
		String ids = set.toString();
		return ids.substring(1, ids.length() - 1);
	}

	/**
	 * �ж� a �Ƿ��ڡ�a,b,c��������
	 * 
	 * @author xiehz
	 * @create_date 2015-1-27 ����5:12:27
	 * @param org
	 * @param compArray
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> boolean isIn(T org, T... compArray) {
		for (T t : compArray) {
			if (t.equals(org)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ���ֺ���ĸ���
	 */
	public static final Pattern numberAlphaPattern = Pattern.compile("^[A-Za-z0-9]+$");

	public static boolean isNumberAlphaFix(String str) {
		return numberAlphaPattern.matcher(str).matches();
	}

	/**
	 * 
	 * @Title: getPropertyValue
	 * @Description: ��ȡʵ��bean����ֵ
	 * @param @param bean
	 * @param @param propertyName
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@SuppressWarnings("finally")
	public static Object getPropertyValue(Object bean, String propertyName) {
		Object result = null;
		if (propertyName.equals("serialVersionUID")) {
			return result;
		}
		PropertyDescriptor pd;
		try {
			pd = new PropertyDescriptor(propertyName, bean.getClass());
			Method m = pd.getReadMethod();
			result = m.invoke(bean);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return result;
		}
	}

	/**
	 * 
	 * @Title: setProperty
	 * @Description: ����ʵ��bean������ֵ
	 * @param @param bean
	 * @param @param propertyName
	 * @param @param value
	 * @return void
	 * @throws
	 */
	public static void setProperty(Object bean, String propertyName, Object value) {
		PropertyDescriptor pd;
		try {
			pd = new PropertyDescriptor(propertyName, bean.getClass());
			Method m = pd.getWriteMethod();
			// ��������ֵ
			m.invoke(bean, value);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ������ ֵcope������
	 * �������� ��public
	 * @param father
	 * @param child
	 * @return
	 * @throws Exception
	 */
	public static Object fatherToChild(Object father, Object child) throws Exception {
		if (!(child.getClass().getSuperclass() == father.getClass())) {
			throw new Exception("child����father������");
		}
		Class fatherClass = father.getClass();
		Field ff[] = fatherClass.getDeclaredFields();
		for (int i = 0; i < ff.length; i++) {
			Field f = ff[i];// ȡ��ÿһ�����ԣ���deleteDate
			Class type = f.getType();
			String nameString = upperHeadChar(f.getName());
			if (!"SerialVersionUID".equals(nameString)) {
				Method m = fatherClass.getMethod("get" + upperHeadChar(f.getName()));// ����getDeleteDate
				Object obj = m.invoke(father);// ȡ������ֵ
				f.set(child, obj);
			}
		}
		return child;
	}

	/**
	 * ����ĸ��д��in:deleteDate��out:DeleteDate
	 */
	private static String upperHeadChar(String in) {
		String head = in.substring(0, 1);
		String out = head.toUpperCase() + in.substring(1, in.length());
		return out;
	}

}
