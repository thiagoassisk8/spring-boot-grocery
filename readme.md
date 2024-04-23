**API DOCUMENTATION**
Api Documentation: [Link](https://immense-thicket-69297-ae6d8599f1bc.herokuapp.com/api-docs/)

**Abstraction**
![Image Description](imgs/diagram.png)

1. **How long did you spend on the test? What would you add if you had more time?**
   I spend about 9 hours within 3 days on the test
   If I had more time, I would have added more unit tests to ensure there are no breaches. I believe the more tests you put on the application, the better it gets because then we become better able to see more breaches that weren't found
   Additionally, if I had extra time, I would make it possible to override the system and allow the operator to insert the coupon’s ID, and then the discount is given. They would simply need to enter the coupon ID correctly, and the system would apply it automatically, regardless of the circumstances. The way I approached it was to have the coupon applied if the circumstances match, not the other way around.
   And improvemnts reguarding Error Handling. The excpetions that the application should deal with that weren't done given the lack of time to test more the application

2. **What was the most useful feature that was added to the latest version of your chosen language? Please include a snippet of code that shows how you've used it?**

```java
    public Product fetchProductById(String productId) throws IOException {
        String url = getEndpoint() + "/products/" + productId;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                String errorMessage = String.format("There's no product with ID %s: %s", productId, response);
                throw new IOException(errorMessage);
            }

            return parseResponse(response, Product.class);
        }
    }

    private <T> T parseResponse(Response response, Type type) throws IOException {
        return gson.fromJson(response.body().string(), type);
    }
```

If we are talking about my usage that would be the snippet above because whenever I find myself in a tight spot needing info from WireMock, it came to the rescue. It was like having a handy tool in my toolbox, making data retrieval easy.
However if we were going to consider for general use more people would work on this same project I would say the [documentation](https://immense-thicket-69297-ae6d8599f1bc.herokuapp.com/api-docs/) becuase would be make it easier to peopole get know the api.

3. **What did you find most difficult?**

   The most challenging aspect was thinking about a solution that effectively addressed all the system's requirements and turning it into code. Given the object-oriented nature of the codebase, it was crucial to have a clear understanding of the solution before diving into implementation. Specifically, I struggled with handling the functionality of discounting within a tight timeframe.

4. **What mechanism did you put in place to track down issues in production on this code? If you didn’t put anything, write down what you could do.**

   I set up a couple of ways to track down problems when I was coding. First off, I did a lot of debugging to really dive deep into the data and find any issues. And then, I made sure to use the spring-boot-devtools package so I could do maintenance on the server in real-time. That way, I could quickly jump in and fix anything that came up.

5. **The Wiremock represents one source of information. We should be prepared to integrate with more sources. List the steps that we would need to take to add more sources of items with diferent formats and promotions.**

**Step 1**
Add a new type of Cupom (src/main/java/com/qikserver/grocery/entities/CupomType.java)
given that diferent type of promotions would come in

**Step 2**
Given this information it seems prudent to introduce a new CupomController to improve the project's adaptability to future requirements. further more, it's advisable to implement a CupomService class (src/main/java/com/qikserver/grocery/services/CupomService.java) to streamline the management of cupom-related operations. These additions will not only improve the project's scalability but also facilitate the integration of forthcoming functionalities.

**Describe the most innovative or inventive endeavor you've undertaken. This could be your idea for a process change, a new product concept, a unique metric, or a novel customer interface. Do not share confidentail information! Provide context to help us understand the innovation. What problem were you addressing, and what were the outcomes? Why was solving this problem important, and what was the impact of the change?**
So a company I worked for developed an app similar to iFood, but for home renovation services. Initially the app only worked on mobile devices and used the user's location to connect clients with nearby service providers.

Later when they expanded the app to a web version, real estate agencies started requesting services for their clients, but faced an issue: they couldn't provide the precise location of the clients (hosts).

To solve this my thogught was to create a form where real estate agencies input the host' addresses. Then, this address is converted into geolocation, allowing the service to be provided effectively, even without the exact location of the clients.

Additionally, to implement this solution, I had to research which resources could assist in this process. That's when I found out about the Google Cloud Platform (GCP), which offers tools to handle geolocation and convert addresses into geographic coordinates. This discovery enabled the creation of the form that exchanges data for geolocation, providing an effective solution for real estate agencies to request services for hosts.

I used an old-school Google Docs to tap down my initial thoughts on this problem.
![Image Description](imgs/screenshot.png)
