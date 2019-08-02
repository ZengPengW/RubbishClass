package com.zp.rubbish.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zp.rubbish.entities.Rubbish;
import com.zp.rubbish.entities.TbFeedback;
import com.zp.rubbish.entities.TbRubbish;
import com.zp.rubbish.mapper.FeedbackMapper;
import com.zp.rubbish.mapper.RubbishMapper;
import com.zp.rubbish.service.RubbishClassService;
import com.zp.rubbish.utils.APIService;
import com.zp.rubbish.utils.EasyUIDataGridResult;
import com.zp.rubbish.utils.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RubbishClassServiceImpl implements RubbishClassService {

    @Value("${HTTP_HOST}")
    private String HTTP_HOST;

    @Autowired
    private RubbishMapper rubbishMapper;

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Autowired
    private APIService apiService;



    @Override
    public Rubbish selectRubbishClassByName(String name) throws Exception {

        Rubbish rubbish = rubbishMapper.selectRubbileByName(name);
        if(rubbish!=null){
            return rubbish;
        }
        rubbish=new Rubbish();
        rubbish.setRubbishName(name);
        rubbish.setRubbishClass("未知垃圾等待更新");
        HttpResult httpResult = apiService.doGet(HTTP_HOST + name);

        if (httpResult.getCode() == 200) {
            String body = httpResult.getBody();
            int index = KPMsearch(body, "&nbsp;属于");
            if(index!=-1){
                body=body.substring(index);
                index=KPMsearch(body, "垃圾");
                for (int i =index; i >=0 ; i--) {
                    if(body.charAt(i)=='>'){
                        body=body.substring(i+1,index+2);
                        break;
                    }
                }
                rubbish.setRubbishClass(body);
                //保存垃圾到数据库
                TbRubbish tbRubbish=new TbRubbish();
                tbRubbish.setRubbishName(name);
                /*
                    1  有害垃圾
                    2  湿垃圾
                    3  干垃圾
                    4  可回收垃圾
                 */
                if (body.contains("有害")){
                    tbRubbish.setRubbishClass(1);
                }else if (body.contains("湿")){
                    tbRubbish.setRubbishClass(2);
                }else if (body.contains("干")){
                    tbRubbish.setRubbishClass(3);
                }else {
                    tbRubbish.setRubbishClass(4);
                }
                rubbishMapper.save(tbRubbish);



                return rubbish;
            }

        }

        TbFeedback tbFeedback = feedbackMapper.selectFeedbackByName(name);
        if (tbFeedback==null){
            feedbackMapper.saveFeedback(name);
        }

        return rubbish;
    }

    @Override
    public void saveRbuuish(TbRubbish tbRubbish) {
        rubbishMapper.save(tbRubbish);
    }

    @Override
    public EasyUIDataGridResult<TbRubbish> selectAll(Integer page, Integer rows) {
        if (page == null)
            page = 1;
        if (rows == null)
            rows = 30;
        PageHelper.startPage(page, rows);
        List<TbRubbish> list = rubbishMapper.selectAll();
        PageInfo<TbRubbish> pageInfo = new PageInfo<TbRubbish>(list);
        EasyUIDataGridResult<TbRubbish> dataGridResult = new EasyUIDataGridResult<>();
        dataGridResult.setRows(list);
        dataGridResult.setTotal(pageInfo.getTotal());
        return dataGridResult;
    }

    // KPM子字符串查找算法
    public static int[] getnext(String pat) {
        int len = pat.length();
        int[] next = new int[len];
        char[] p = pat.toCharArray();
        int j = 0;
        int k = -1;
        next[0] = -1;
        while (j < len - 1) {

            if (k == -1 || p[j] == p[k]) {
                ++j;
                ++k;
                if (p[j] == p[k])
                    next[j] = next[k];
                else
                    next[j] = k;
            } else {
                k = next[k];
            }
        }

        return next;
    }

    /**
     * @param str 原来字符串
     * @param pat 需要匹配的字符串
     * @return
     */
    public static int KPMsearch(String str, String pat) {
        int[] next = getnext(pat); //由于一个pat要匹配多次 提取到参数里省的每次生成 浪费时间
        char[] s = str.toCharArray();
        char[] p = pat.toCharArray();
        int slen = s.length;
        int plen = p.length;

        int i = 0, j = 0;
        while (i < slen && j < plen) {
            if (j == -1 || s[i] == p[j]) {
                ++i;
                ++j;
            } else {
                j = next[j];
            }
        }
        if (j == plen)
            return i - j;
        return -1;

    }
}
