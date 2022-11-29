package com.model2_cgv.service;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.model2_cgv.vo.CgvBoardVO;
import com.model2_cgv.vo.CgvNoticeVO;

@Service
public class FileServiceImpl {
	/**
	 * �Խ��� : ���� üũ �� bfile, bsfile ����
	 */
	public CgvBoardVO fileCheck(CgvBoardVO vo) {
		//1. vo��ü�� ����üũ �� bfile, bsfile�� ����Ǵ� �̸� ����
		if(vo.getFile1().getOriginalFilename().equals("")) {
			vo.setBfile("");
			vo.setBsfile("");
		}else {
			UUID uuid = UUID.randomUUID();
			vo.setBfile(vo.getFile1().getOriginalFilename());
			vo.setBsfile(uuid + "_" + vo.getFile1().getOriginalFilename());
		}
		
		return  vo;
	}//fileCheck
	
	/**
	 * �Խ��� : ���� upload ������ ����
	 */
	public void fileSave(CgvBoardVO vo, HttpServletRequest request) throws Exception {
		//2. upload ������ bsfile ������ ���� ���� ���ε� ó��
		if(!vo.getFile1().getOriginalFilename().equals("")) {
			String path = request.getSession().getServletContext().getRealPath("/");
			path += "\\resources\\upload\\";
			
			File file = new File(path+vo.getBsfile());
			vo.getFile1().transferTo(file);
		}
	}
	
	/**
	 * �Խ��� : ������ �ִ� ��� update�� ����üũ
	 */
	public CgvBoardVO update_fileCheck(CgvBoardVO vo) {
		if(vo.getFile1() != null) {
			if(!vo.getFile1().getOriginalFilename().equals("")) { //���ο� ���ϼ��� O
				
				UUID uuid = UUID.randomUUID();
				
				vo.setBfile(vo.getFile1().getOriginalFilename());
				vo.setBsfile(uuid+"_"+vo.getFile1().getOriginalFilename());
			}
		}
		return vo;
	}
	
	/**
	 * �Խ��� : ������ �ִ� ��� update�� ��������
	 */
	public void update_filesave(CgvBoardVO vo, HttpServletRequest request, String old_filename) 
														throws Exception {
		//���ο� ������ upload ������ ����
		if(!vo.getFile1().getOriginalFilename().equals("")) { //���ο� ���ϼ��� O
			String path = request.getSession().getServletContext().getRealPath("/");
			path += "\\resources\\upload\\";
			System.out.println(path);
			
			File file = new File(path+vo.getBsfile());
			vo.getFile1().transferTo(file);
		
			//���������� �ִ� ��쿡�� ���� ����
			File ofile = new File(path+old_filename);
			if(ofile.exists()) {
				ofile.delete();
			}
		}
	}
	
	/**
	 * �Խ��� : �Խñ� �輼 �� ������ �����ϸ� �����ϱ�
	 */
	public void fileDelete(CgvBoardVO vo, HttpServletRequest request) throws Exception{
		if(vo.getBsfile() != null) {
			String path=request.getSession().getServletContext().getRealPath("/");
			path += "\\resources\\upload\\";
			
			File old_file = new File(path+vo.getBsfile());
			if(old_file.exists()) {
				old_file.delete();
			}
		}
	}
	
	/**
	 * �������� : ���� üũ �� nfile, nsfile ����
	 */
	public CgvNoticeVO fileCheck(CgvNoticeVO vo) {
		//1. vo��ü�� ����üũ �� nfile, nsfile�� ����Ǵ� �̸� ����
		if(vo.getFile1().getOriginalFilename().equals("")) {
			vo.setNfile("");
			vo.setNsfile("");
		}else {
			UUID uuid = UUID.randomUUID();
			vo.setNfile(vo.getFile1().getOriginalFilename());
			vo.setNsfile(uuid + "_" + vo.getFile1().getOriginalFilename());
		}
		
		return  vo;
	}//fileCheck
	
	/**
	 * �������� : ���� upload ������ ����
	 */
	public void fileSave(CgvNoticeVO vo, HttpServletRequest request) throws Exception {
		//2. upload ������ nsfile ������ ���� ���� ���ε� ó��
		if(!vo.getFile1().getOriginalFilename().equals("")) {
			String path = request.getSession().getServletContext().getRealPath("/");
			path += "\\resources\\upload\\";
			
			File file = new File(path+vo.getNsfile());
			vo.getFile1().transferTo(file);
		}
	}
	
	/**
	 * �������� : ������ �ִ� ��� update�� ����üũ
	 */
	public CgvNoticeVO update_fileCheck(CgvNoticeVO vo) {
		if(vo.getFile1() != null) {	//���ο� ���ϰ�ü�� �ִ��� ����üũ �ϴ� ��쿡�� null�� ���
			if(!vo.getFile1().getOriginalFilename().equals("")) { //���ο� ���ϼ��� O
				
				UUID uuid = UUID.randomUUID();
				
				vo.setNfile(vo.getFile1().getOriginalFilename());
				vo.setNsfile(uuid+"_"+vo.getFile1().getOriginalFilename());
			}
		}
		return vo;
	}
	
	/**
	 * �������� : ������ �ִ� ��� update�� ��������
	 */
	public void update_filesave(CgvNoticeVO vo, HttpServletRequest request, String old_filename) 
														throws Exception {
		//���ο� ������ upload ������ ����
		if(!vo.getFile1().getOriginalFilename().equals("")) { //���ο� ���ϼ��� O
			String path = request.getSession().getServletContext().getRealPath("/");
			path += "\\resources\\upload\\";
			System.out.println(path);
			
			File file = new File(path+vo.getNsfile());
			vo.getFile1().transferTo(file);
		
			//���������� �ִ� ��쿡�� ���� ����
			File ofile = new File(path+old_filename);
			if(ofile.exists()) {
				ofile.delete();
			}
		}
	}
	
	/**
	 * �������� : �Խñ� �輼 �� ������ �����ϸ� �����ϱ�
	 */
	public void fileDelete(CgvNoticeVO vo, HttpServletRequest request) throws Exception{
		if(vo.getNsfile() != null) {
			String path=request.getSession().getServletContext().getRealPath("/");
			path += "\\resources\\upload\\";
			
			File old_file = new File(path+vo.getNsfile());
			if(old_file.exists()) {
				old_file.delete();
			}
		}
	}
}
