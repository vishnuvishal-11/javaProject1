//package com.example.dbproject.dao.User;
//
//import com.example.dbproject.model.user.TringMembership;
//import com.example.dbproject.repository.userSection.TringMembershipRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class MembershipDaoImpl<T> implements MembershipDAO<T> {
//
//    @Autowired
//    TringMembershipRepo tring_membership_repo;
//
//    @Override
//    public Long save(T obj) {
//            tring_membership_repo.save((TringMembership) obj);
//       //     return ((Tring_membership) obj).getUser().getId();
//        return ((TringMembership) obj).getUsers().getId();
//    }
//
//    @Override
//    public T update(T obj) {
//        return getById(save(obj));
//    }
//
//    @Override
//    public void delete(Long id) {
//       tring_membership_repo.deleteById(id);
//
//    }
//
//    @Override
//    public T getById(Long id) {
//        return (T) tring_membership_repo.findById(id).orElse(null);
//    }
//
//    @Override
//    public List<T> getAll() {
//        return (List<T>) tring_membership_repo.findAll();
//    }
//
//    @Override
//    public Boolean existsById(Long id) {
//        return tring_membership_repo.existsById(id);
//    }
//
//
//}
