//package com.example.securingweb.dao;
//
//import javax.persistence.EntityManager;
//
//import com.example.securingweb.entity.LoginUser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
///**
// * DBへのアクセスメソッドを呼び出すDao
// *
// */
//@Repository
//public class LoginUserDao {
//
//    /**
//     * エンティティを管理するオブジェクト。
//     * 以下のメソッドでエンティティクラスであるLoginUserにキャストして戻り値を返すので必要なオブジェクト。
//     */
//    @Autowired
//    EntityManager em;
//
//    /**
//     * フォームの入力値から該当するユーザを検索 合致するものが無い場合Nullが返される
//     * @param name
//     * @return 一致するユーザが存在するとき:UserEntity、存在しないとき:Null
//     */
//    public LoginUser findUser(String name) {
//
//        String query = "";
//        query += "SELECT * ";
//        query += "FROM user ";
//        query += "WHERE name = :name "; //setParameterで引数の値を代入できるようにNamedParameterを利用
//
//        //EntityManagerで取得された結果はオブジェクトとなるので、LoginUser型へキャストが必要となる
//        return (LoginUser)em.createNativeQuery(query, LoginUser.class).setParameter("name", name).getSingleResult();
//    }
//
//}
