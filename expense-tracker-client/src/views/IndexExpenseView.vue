<template>
    <div class="container">
        <div class="form">
            <header>View Expenses</header>
            <ul>
                <li v-for="(expense, index) in expensesStore.expenses"
                    :key="index"
                    :value="expense.id">
                    <RouterLink @click="select(expense)" :to="'/expenses/edit/' + expense.id">{{ expense.description }} - {{ expense.amount }} BGN</RouterLink>
                    <button @click="destroy(expense.id)">Delete</button>
                </li>
            </ul>
        </div>
    </div>
</template>

<script setup>
import '@vuepic/vue-datepicker/dist/main.css';
import { useExpensesStore } from '@/stores/expensesStore';
import { useAuthStore } from '@/stores/authStore';

const authStore = useAuthStore();

const expensesStore = useExpensesStore();
expensesStore.getExpenses();

const destroy = async (id) => {
    await fetch('/api/expenses/' + id,  {
        method: 'DELETE',
        headers: {
            "Content-Type": "application/json", 
            "Authorization": "Bearer " + authStore.getToken,
        },
    });

    expensesStore.getExpenses();
};

function select(selectedExpense) {
    expensesStore.setSelectedExpense(
        selectedExpense.description, 
        selectedExpense.amount, 
        selectedExpense.categoryId, 
        selectedExpense.date,
    );
    localStorage.setItem('description', selectedExpense.description);
    localStorage.setItem('amount', selectedExpense.amount);
    localStorage.setItem('categoryId', selectedExpense.categoryId);
    localStorage.setItem('date', selectedExpense.date);
}
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
