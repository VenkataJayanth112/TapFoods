<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Login-In</title>
<style>
    body {
        font-family: 'Roboto', sans-serif;
        background-image: url('<%= request.getContextPath() %>/images/background_.jpg');   /** Set your background image URL here **/
        background-size: cover;
        background-position: center;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .grid-container {
        display: grid;
        grid-template-columns: auto;
        grid-template-rows: auto;
        gap: 10px;
    }

    form {
        background-color: rgba(255, 255, 255, 0.4); /* More translucent background */
        border: 1px solid #dddddd;
        border-radius: 12px;
        padding: 20px;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        width: 100%;
        max-width: 500px;
        margin: 0; 
        position: fixed; /* Keep it floating */
        top: 50%; /* Center it vertically */
        left: 50%; /* Center it horizontally */
        transform: translate(-50%, -50%); /* Adjust the form to be exactly centered */
    }

    form:hover {
        transform: translate(-50%, -50%) translateY(-10px);
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
    }

    h2 {
        text-align: center;
        color: #333333;
        margin-bottom: 20px;
        font-size: 28px;
    }

    h5 {
        text-align: left;
        color: #666666;
        margin-bottom: 10px;
        font-size: 14px;
    }

    h5 a {
        color: #666666; /* Black color for the link */
        text-decoration: underline ;
        font-weight: bold; /* Makes the link stand out more */
    }

    h5 a:hover {
        text-decoration: underline;
        color: #333333; /* Darker shade of black when hovered */
    }

    label {
        display: block;
        margin-bottom: 8px;
        color: #333333;
        font-weight: bold;
    }

    input[type="text"],
    input[type="password"] {
        width: 100%;
        padding: 12px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 6px;
        box-sizing: border-box;
        font-size: 16px;
        background-color: #f4f4f4;
        color: #333333;
    }

    input[type="submit"] {
        width: 100%;
        padding: 12px;
        background-color: #3498db;
        color: #ffffff;
        border: none;
        border-radius: 6px;
        font-size: 16px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    input[type="submit"]:hover {
        background-color: #2980b9;
    }

    .terms {
        font-size: 12px;
        color: #666666;
        text-align: center;
    }

    @media only screen and (max-width: 600px) {
        form {
            width: 90%;
            padding: 15px;
        }

        h2 {
            font-size: 24px;
        }

        input[type="text"],
        input[type="password"],
        input[type="submit"] {
            font-size: 14px;
        }
    }
</style>
</head>
<body>

    <div class="grid-container">

        <div>
            <%
            String error = request.getParameter("error");
            if(error != null)
            {
                if(error.equals("max_attempts")) {%>
                <p>Maximum Login attempts reached. Please try again later.</p>
            <% }else if(error.equals("invalid_credentials")){ %>
                <p>Invalid credentials. Please check your userName and password.</p>
            <% 
            }
            }
            %>
        </div>

        <div>

        <form action="LoginServlet" method="post">
            <h2>Login</h2>
            <h5 style="color:black">Or <a href="SignUp.jsp">Create account</a></h5>
            <label for="username">UserName:</label>
            <input type="text" id="username" name="username" required placeholder="Enter UserName">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required placeholder="Enter Password">
            <input type="submit" value="Login">
            <h6 class="terms" style="color:black">By clicking on Login, I accept the <a href="#" style="color:black">Terms and Conditions</a> and <a href ="#" style="color:black"> Privacy Policy</a></h6>
        </form>
        </div>
    </div>

</body>
</html>
