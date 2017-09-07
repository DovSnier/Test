package com.dvsnier.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lzw on 2017/8/21.
 */
@Entity public class Student {

  @Id(autoincrement = true) private Long id;
  @Index(unique = true) private String number;
  @Property private String name;
  @Property private String sex;

  @Generated(hash = 1000652110) public Student(Long id, String number, String name, String sex) {
    this.id = id;
    this.number = number;
    this.name = name;
    this.sex = sex;
  }

  @Generated(hash = 1556870573) public Student() {
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNumber() {
    return this.number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSex() {
    return this.sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }
}
