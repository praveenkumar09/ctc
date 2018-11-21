package com.censof.myfi.hidefmyfi.vo;

import java.util.List;

public class NameTypeVo {
	private int id;
	private String precedingTitle;
	private String nameType;
	private String firstName;
	private List<TitleVo> titleList;
	private List<MiddleNameVo> middlenameList;
	private String namePrefix;
	private String lastName;
	private List<GenerationIdentifierVo> generateIdentifilerList;
	private List<SuffixVo> suffixList;
	private String generalSuffix;
	private String name;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPrecedingTitle() {
		return precedingTitle;
	}
	public void setPrecedingTitle(String precedingTitle) {
		this.precedingTitle = precedingTitle;
	}
	public String getNameType() {
		return nameType;
	}
	public void setNameType(String nameType) {
		this.nameType = nameType;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public List<TitleVo> getTitleList() {
		return titleList;
	}
	public void setTitleList(List<TitleVo> titleList) {
		this.titleList = titleList;
	}
	public List<MiddleNameVo> getMiddlenameList() {
		return middlenameList;
	}
	public void setMiddlenameList(List<MiddleNameVo> middlenameList) {
		this.middlenameList = middlenameList;
	}
	public String getNamePrefix() {
		return namePrefix;
	}
	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<GenerationIdentifierVo> getGenerateIdentifilerList() {
		return generateIdentifilerList;
	}
	public void setGenerateIdentifilerList(
			List<GenerationIdentifierVo> generateIdentifilerList) {
		this.generateIdentifilerList = generateIdentifilerList;
	}
	public List<SuffixVo> getSuffixList() {
		return suffixList;
	}
	public void setSuffixList(List<SuffixVo> suffixList) {
		this.suffixList = suffixList;
	}
	public String getGeneralSuffix() {
		return generalSuffix;
	}
	public void setGeneralSuffix(String generalSuffix) {
		this.generalSuffix = generalSuffix;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
