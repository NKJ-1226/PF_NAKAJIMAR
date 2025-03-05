package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserInfo;

/**
 * ユーザー情報テーブルRepositoryクラス
 * 
 * @author nakajima
 *
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

}