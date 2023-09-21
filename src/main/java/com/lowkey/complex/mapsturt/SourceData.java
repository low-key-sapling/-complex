package com.lowkey.complex.mapsturt;

public class SourceData {

 private String id;
 private String name;
 private TestData data;
        private Long createTime;

 public String getId() {
  return id;
 }
 public void setId(String id) {
  this.id = id;
 }
 public String getName() {
  return name;
 }
 public void setName(String name) {
  this.name = name;
 }
 public TestData getData() {
  return data;
 }
 public void setData(TestData data) {
  this.data = data;
 }
        public Long getCreateTime() {
  return createTime;
 }
 public void setCreateTime(Long createTime) {
  this.createTime = createTime;
 }
}