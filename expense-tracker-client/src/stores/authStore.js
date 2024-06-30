import { defineStore } from "pinia";
import { ref, computed } from "vue";

export const useAuthStore = defineStore('auth', () => {
    const userId = ref(0);
    const username = ref("");
    const token = ref("hi");

    if (localStorage.getItem("userId")) {
        userId.value = localStorage.getItem("userId");
    }

    if (localStorage.getItem("username")) {
        username.value = localStorage.getItem("username");
    }

    if (localStorage.getItem("token")) {
        token.value = localStorage.getItem("token");
    }

    const getToken = computed(() => {
        return token.value;
    });

    const getUsername = computed(() => {
        return username.value;
    });

    const getUserId = computed(() => {
        return userId.value;
    });

    const setData = (newUserId, newUsername, newToken) => {
        userId.value = newUserId;
        username.value = newUsername;
        token.value = newToken;
    };

    const clearData = () => {
        userId.value = 0;
        username.value = "";
        token.value = "";
        localStorage.removeItem('userId');
        localStorage.removeItem('username');
        localStorage.removeItem('token');
    };
  
    return {
        setData,
        clearData,
        getToken,
        getUsername,
        getUserId,
    };
});