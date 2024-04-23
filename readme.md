Api Documentation: [Link](https://immense-thicket-69297-ae6d8599f1bc.herokuapp.com/api-docs/)

1. **How long did you spend on the test? What would you add if you had more time?**

   I would add more unit tests to ensure there are no breaches. Additionally, if I had extra time, I would make it possible to override the system and allow the operator to insert the coupon’s ID, and then the discount is given. They would simply need to enter the coupon ID correctly, and the system would apply it automatically, regardless of the circumstances. The way I approached it was to have the coupon applied if the circumstances match, not the other way around.

2. **What was the most useful feature that was added to the latest version of your chosen language? Please include a snippet of code that shows how you've used it?**

3. **What did you find most difficult?**

   The most challenging aspect was devising a solution that effectively addressed all the system's requirements and translating it into code. Given the object-oriented nature of the codebase, it was crucial to have a clear understanding of the solution before diving into implementation. Specifically, I struggled with handling the functionality of discounting within a tight timeframe.

4. **What mechanism did you put in place to track down issues in production on this code? If you didn’t put anything, write down what you could do.**

   I set up a couple of ways to track down problems when I was coding. First off, I did a lot of debugging to really dive deep into the data and find any issues. And then, I made sure to use the spring-boot-devtools package so I could do maintenance on the server in real-time. That way, I could quickly jump in and fix anything that came up.

5. **The Wiremock represents one source of information. We should be prepared to integrate with more sources. List the steps that we would need to take to add more sources of items with diferent formats and promotions.**
