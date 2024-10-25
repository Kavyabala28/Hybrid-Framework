Feature: LeafTab create Lead

Scenario: Create Lead

Given Enter username
And Enter password
When Click Login
And Click crmsfa
And Click create Lead
Given Enter companyname <cname>
And Enter firstname <fname>
And Enter lastname <lname>
When Click submit

