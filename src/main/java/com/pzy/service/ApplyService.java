
package com.pzy.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.pzy.entity.Apply;
import com.pzy.entity.Project;
import com.pzy.entity.User;
import com.pzy.repository.ApplyRepository;

@Service
public class ApplyService {
     @Autowired
     private ApplyRepository applyRepository;
     public Page<Apply> findAll(final int pageNumber, final int pageSize,final Project project){
           PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
           Specification<Apply> spec = new Specification<Apply>() {
                @Override
                public Predicate toPredicate(Root<Apply> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (project != null) {
                     predicate.getExpressions().add(cb.equal(root.get("project").as(Project.class),project));
                }
                return predicate;
                }
           };
           Page<Apply> result = (Page<Apply>) applyRepository.findAll(spec, pageRequest);
           return result;
     }
     public Page<Apply> findAll(final int pageNumber, final int pageSize,final Project project,final User user){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Apply> spec = new Specification<Apply>() {
              @Override
              public Predicate toPredicate(Root<Apply> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (project != null) {
                   predicate.getExpressions().add(cb.equal(root.get("project").as(Project.class),project));
              }
              if (user != null) {
                  predicate.getExpressions().add(cb.equal(root.get("user").as(User.class),user));
             }
              return predicate;
             }
         };
         Page<Apply> result = (Page<Apply>) applyRepository.findAll(spec, pageRequest);
         return result;
   }
     public Page<Apply> findAll(final int pageNumber, final int pageSize,final Project project,final User user,final String state){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Apply> spec = new Specification<Apply>() {
              @Override
              public Predicate toPredicate(Root<Apply> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (project != null) {
                   predicate.getExpressions().add(cb.equal(root.get("project").as(Project.class),project));
              }
              if (user != null) {
                  predicate.getExpressions().add(cb.equal(root.get("user").as(User.class),user));
             }
              if (state != null) {
                  predicate.getExpressions().add(cb.equal(root.get("state").as(String.class),state));
             }
              return predicate;
             }
         };
         Page<Apply> result = (Page<Apply>) applyRepository.findAll(spec, pageRequest);
         return result;
   }
     public List<Apply> findAll(){
    	 return (List<Apply>) this.applyRepository.findAll();
     }
     public List<Apply> findAll(User user){
    	 return (List<Apply>) this.applyRepository.findAll();
     }
     public List<Apply> findAllForCheck(User user,String state){
    	 return (List<Apply>) this.applyRepository.findByUserAndState(user, state);
     }
     public void delete(Long id){
    	 applyRepository.delete(id);
     }
     public Apply find(Long id){
    	  return applyRepository.findOne(id);
     }
     public void save(Apply apply){
    	 applyRepository.save(apply);
     }
}