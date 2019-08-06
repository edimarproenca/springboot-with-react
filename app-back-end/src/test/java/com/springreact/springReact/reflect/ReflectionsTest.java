package com.springreact.springReact.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.springreact.springReact.model.FuelType;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@Slf4j
public class ReflectionsTest {

	private void accessGetNameMethod(FuelType fuelType) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for (Method m : fuelType.getClass().getMethods()) {
			if (StringUtils.startsWithIgnoreCase(m.getName(), "getName")) {
				log.info(m.getName());
					m.setAccessible(true);
					log.info(m.invoke(fuelType, m.getParameterTypes()).toString());
			}
		}
	}

	@Test
	public void runTest() {
		FuelType fuelType = new FuelType("Gasolina");
		try {
			this.accessGetNameMethod(fuelType);			
			Map<String, String> methodMap = this.getMethodsToMap(fuelType);
		
			methodMap.forEach((key, value) -> {
				log.info("Key -> " + key +"()");
				log.info("Value -> " + value);
			});
		
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Map<String, String> getMethodsToMap(FuelType fuelType) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Map<String, String> result = new HashMap<String, String>();
		
		Method[] methodList = fuelType.getClass().getMethods();
		
		for(Method met : methodList) {
			if(StringUtils.startsWithIgnoreCase(met.getName(), "get")) {
				if(met.getGenericReturnType().equals(String.class)) {
					result.put(met.getName(),(String)met.invoke(fuelType, met.getParameterTypes()));					
				}
			}
		}
		return result;
	}

	

}
