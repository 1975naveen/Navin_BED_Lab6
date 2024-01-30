package com.enterprise.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.enterprise.model.Coutry;
import com.enterprise.model.User;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CustomSpecification implements Specification<PagingRequest>{
	//private String countryName="In";
	private PagingRequest request;
	public CustomSpecification(PagingRequest request){
		this.request=request;
	}
	@Override
	public Predicate toPredicate(Root<PagingRequest> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicate=new ArrayList<Predicate>(); 
		Join<User,Coutry> join=root.join("coutry");
		predicate.add(criteriaBuilder.like(root.get("name"),"%"+ request.getName()+"%"));
		predicate.add(criteriaBuilder.like(root.get("email"),"%"+ request.getEmail()+"%"));
		if(request.getDate()!=null) {
			System.out.println("--------------------"+request.getDate());
			predicate.add(criteriaBuilder.greaterThan(root.get("createdAt"), request.getDate().atStartOfDay().plusDays(1)));
		}
		if(request.getUserid()!=0) {
			predicate.add(criteriaBuilder.between(root.get("id"), request.getUserid(), 15));
		}
		
		predicate.add(criteriaBuilder.like(root.get("coutry").get("name"),"%" +request.getCoutry()+"%"));
		return criteriaBuilder.and(predicate.toArray(new Predicate[0]));
	}

}
