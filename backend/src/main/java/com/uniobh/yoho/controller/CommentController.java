package com.uniobh.yoho.controller;

import com.uniobh.yoho.service.CommentService;
import com.uniobh.yoho.utils.ResultUtil;
import com.uniobh.yoho.vo.AddCommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    public ResultUtil<Boolean> addCommentByAddCommentVo(@RequestBody AddCommentVo addCommentVo) {
        return commentService.addCommentByAddCommentVo(addCommentVo);
    }

    @PutMapping("/comment/{id}/{uId}")
    public ResultUtil<Boolean> addLike(@PathVariable("id") Integer id, @PathVariable("uId") Integer uId) {
        return commentService.addLike(id, uId);
    }
}
