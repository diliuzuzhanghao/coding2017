package com.ecust.test.jvm9;

import static org.junit.Assert.assertThat;

import org.junit.Before;

import org.junit.Test;

import com.ecust.jvm9.BinarySearchTree;

import static org.hamcrest.Matchers.equalTo;

import static org.hamcrest.junit.MatcherAssert.assertThat;



/**

 * Created by john on 2017/3/14.

 */

public class BinarySearchTreeTest {



    private BinarySearchTree bst;



    @Before

    public void setUp() throws Exception {

        bst = new BinarySearchTree();

        bst.insert(bst.getRoot(), 10);

        bst.insert(bst.getRoot(), 20);

        bst.insert(bst.getRoot(), 9);

        bst.insert(bst.getRoot(), 11);

        bst.insert(bst.getRoot(), 12);

    }



    @Test

    public void testFindMin() throws Exception {

        assertThat(bst.findMin(bst.getRoot()), equals(9));



    }



    @Test

    public void testFindMax() throws Exception {

        assertThat(bst.findMax(bst.getRoot()), equals(20));

    }



}
