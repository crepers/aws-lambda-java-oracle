package com.wcjung.dbSample;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

import com.wcjung.dbSample.dao.PersonDAO;
import com.wcjung.dbSample.mybatis.MyBatisConnectionFactory;
import com.wcjung.dbSample.vo.Person;

public class MyOracleConnect {

	private static final Logger LOGGER = LogManager.getLogger(MyOracleConnect.class);
    
	public static void main(String[] args) {
		 // Laod spring-config.xml file
        //ApplicationContext ctx = new ClassPathXmlApplicationContext("com/hmkcode/config/spring-config.xml");
 
		LOGGER.info("start main");
		
		String now = DateTime.now().toString("yyyyMMddHHmmss");
		
		//get jdbcTemplatePersonDAO
        PersonDAO personDAO = new PersonDAO(MyBatisConnectionFactory.getSqlSessionFactory());
 
        //create person bean to insert
        Person person = new Person();
        person.setId(now);
        person.setName("Person " + now);
 
        LOGGER.debug("Person object is : {}", person);
        
        //( 1 ) insert person 
        personDAO.insert(person);
 
        //( 2 ) select persons by id
        personDAO.selectById(person.getId());
 
        //( 3 ) select all
        List<Person> persons = personDAO.selectAll();
 
        //**set name of all persons
        for(int i = 0; i < persons.size(); i++){
        	System.out.println("Name is " + persons.get(i).getId());
        	System.out.println("Name is " + persons.get(i).getName());
//            persons.get(i).setName("Person Name "+i);
//            //( 4 ) update person
//            personDAO.update(persons.get(i));
        }
 
        //**check update
//        persons = personDAO.selectAll();
	}

}
