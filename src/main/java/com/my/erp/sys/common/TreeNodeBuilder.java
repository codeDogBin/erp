package com.my.erp.sys.common;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeBuilder {
    /**
     * 把没有层级关系的集合变成有层级关系的集合
     * @param list
     * @param topID
     * @return
     */
    public static List<TreeNode> build(List<TreeNode> list,Integer topID){
            List<TreeNode> nodes = new ArrayList<>();
        for (TreeNode n1 : list) {
            if(n1.getPid() == topID){//如果PID等于根节点 加入集合中作为1级目录
                nodes.add(n1);
            }
            for (TreeNode n2 : list) {//如果n2节点pid的等于n1节点的ID
                if(n1.getId() == n2.getPid()){
                    if(n1.getChildren() == null){
                       List<TreeNode>childNodes =  new ArrayList<TreeNode>();
                       childNodes.add(n2);
                       n1.setChildren(childNodes);
                    }else{
                        n1.getChildren().add(n2);
                    }
                }
            }
        }
        return nodes;
    }
}
