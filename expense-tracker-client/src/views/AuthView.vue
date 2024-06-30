<template>
<div class="container">
    <input type="checkbox" id="check">
    <div class="login form">
        <header>Login</header>
        <form @submit.prevent="login">
            <input v-model="uname" type="text" placeholder="Enter your username" autocomplete="on">
            <input v-model="pword" type="password" placeholder="Enter your password" autocomplete="on">
            <a href="#">Forgot password?</a>
            <input type="submit" class="button" value="Login">
        </form>
        <div class="form-toggle">
            <span class="form-toggle">Don't have an account?
                <label for="check">Register</label>
            </span>
        </div>
    </div>
    <div class="registration form">
        <header>Register</header>
        <form @submit.prevent="register">
            <input v-model="uname" type="text" placeholder="Enter your username" autocomplete="on">
            <input v-model="pword" type="password" placeholder="Create a password" autocomplete="on">
            <input type="submit" class="button" value="Register">
        </form>
        <div class="form-toggle">
            <span class="form-toggle">Already have an account?
                <label for="check">Login</label>
            </span>
        </div>
    </div>
</div>
</template>

<script setup>
import { ref } from 'vue';
import { useAuthStore } from '@/stores/authStore';
import router from '@/router';

const uname = ref("");
const pword = ref("");

const authStore = useAuthStore();

const login = async () => {
    let response;
    let json;

    try {
        response = await fetch('/api/auth/login', {
            method: "POST",
            headers: {
                "Content-Type": "application/json", 
            },
            body: JSON.stringify({
                username: uname.value, 
                password: pword.value,
            }),
        });

        json = await response.json();
    } catch (error) {
        console.log('There was an error', error);
    }

    console.log(response);
    if (response?.ok) {
        const { userId, username, accessToken } = json;

        authStore.setData(userId, username, accessToken);
        localStorage.setItem('userId', userId);
        localStorage.setItem('username', username);
        localStorage.setItem('token', accessToken);

        router.push('/expenses/create');
    }
};

const register = async () => {
    let response;

    try {
        response = await fetch('/api/auth/register', {
            method: "POST",
            headers: {
                "Content-Type": "application/json", 
            },
            body: JSON.stringify({
                username: uname.value, 
                password: pword.value,
            }),
        });
    } catch (error) {
        console.log('There was an error', error);
    }

    if (response?.ok) {
        login();
    }
};
</script>

<style scoped>
    .container{
        max-width: 430px;
        background: rgba(50, 50, 50, 0.5);
        border-radius: 7px;
    }
    .container .registration{
        display: none;
    }
    #check:checked ~ .registration{
        display: block;
    }
    #check:checked ~ .login{
        display: none;
    }
    #check{
        display: none;
    }
    .container .form{
        padding: 2rem;
    }
    .form header{
        font-size: 2rem;
        font-weight: 500;
        text-align: center;
        margin-bottom: 1.5rem;
    }
    .form input{
        height: 60px;
        width: 100%;
        padding: 0 15px;
        font-size: 17px;
        margin-bottom: 1.3rem;
        border: 1px solid #ddd;
        border-radius: 6px;
        outline: none;
    }
    .form input.button{
        color: #fff;
        background: rgba(255, 123, 172, 1);;
        font-size: 1.2rem;
        font-weight: 500;
        letter-spacing: 1px;
        margin-top: 1.7rem;
        cursor: pointer;
        transition: 0.4s;
    }
    .form input.button:hover{
        background: rgba(255, 123, 172, 0.8);
    }
    .form-toggle{
        font-size: 17px;
        text-align: center;
    }
    .form-toggle label{
        color: rgba(255, 123, 172, 1);
        cursor: pointer;
    }
    .form-toggle label:hover{
        text-decoration: underline;
    }
</style>
