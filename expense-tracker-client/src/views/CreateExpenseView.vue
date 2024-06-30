<template>
    <div class="container">
        <div class="form">
            <header>Add Expense</header>
            <form @submit.prevent="create">
                <input v-model="description" type="text" placeholder="Enter expense description" autocomplete="on">
                <input v-model="amount" type="number" step="0.01" placeholder="Enter expense amount" autocomplete="on">
                <select v-model="categoryId">
                    <option
                      v-for="(category, index) in categoriesStore.categories"
                      :key="index"
                      :value="category.id"
                    >
                      {{ category.title }}
                    </option>
                </select>
                <VueDatePicker v-model="date" :enable-time-picker="false" auto-apply />
                <input type="submit" class="button" value="Add">
            </form>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import '@vuepic/vue-datepicker/dist/main.css';
import { useCategoriesStore } from '@/stores/categoriesStore';
import { useAuthStore } from '@/stores/authStore';

const description = ref("");
const amount = ref(0);
const categoryId = ref(1);
const date = ref();

const authStore = useAuthStore();

const categoriesStore = useCategoriesStore();
categoriesStore.getCategories();

const create = async () => {
    let response;

    try {
        response = await fetch('/api/expenses', {
            method: "POST",
            headers: {
                "Content-Type": "application/json", 
                "Authorization": "Bearer " + authStore.getToken,
            },
            body: JSON.stringify({
                description: description.value, 
                amount: amount.value, 
                categoryId: categoryId.value, 
                date: date.value,
            }),
        });
    } catch (error) {
        console.log('There was an error', error);
    }

    if (response?.ok) {
        description.value = "";
        amount.value = 0;
        categoryId.value = 1;
        date.value = null;
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
.container .form{
    padding: 2rem;
}
.form header{
    font-size: 2rem;
    font-weight: 500;
    text-align: center;
    margin-bottom: 1.5rem;
}
.form input, .form select{
    height: 50px;
    width: 100%;
    padding: 0 13px;
    font-size: 15px;
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
</style>
