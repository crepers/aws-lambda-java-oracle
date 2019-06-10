package com.wcjung.dbSample.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.wcjung.dbSample.vo.Person;

public class PersonDAO {
	private SqlSessionFactory sqlSessionFactory = null;

	public PersonDAO(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	/**
	 * Returns the list of all Person instances from the database.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Person> selectAll() {
		List<Person> list = null;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			list = session.selectList("Person.selectAll");
		} finally {
			session.close();
		}

		System.out.println("SelectAll() --> " + list);

		return list;
	}

	/**
	 * Select instance of Person from the database.
	 * 
	 * @param id
	 * @return
	 */
	public Person selectById(String id) {
		Person person = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			person = session.selectOne("Person.selectById", id);
		} finally {
			System.out.println("SelectById(" + id + ") --> " + person);
		}
		return person;
	}

	/**
	 * Insert an instance of Person into the database.
	 * 
	 * @param person
	 * @return
	 */
	public int insert(Person person) {
		int seq = -1;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			seq = session.insert("Person.insert", person);
		} finally {
			session.commit();
			session.close();
		}
		
		System.out.print("insert result : " + seq);
		System.out.println("\tinsert(" + person + ") -->" + person.getId());
		return seq ;
	}

	/**
	 * Update an instance of Person into the database.
	 * 
	 * @param person the instance to be persisted.
	 */
	public void update(Person person) {
		int seq = -1;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			seq = session.update("Person.update", person);
		} finally {
			session.commit();
			session.close();
		}
		System.out.print("update result : " + seq);
		System.out.println("update(" + person + ") --> updated");
	}

	/**
	 * Delete an instance of Person from the database.
	 * 
	 * @param id value of the instance to be deleted.
	 */
	public void delete(String id) {
		SqlSession session = sqlSessionFactory.openSession();

		try {
			session.delete("Person.delete", id);
		} finally {
			session.commit();
			session.close();
		}
		System.out.println("delete(" + id + ")");
	}
}
