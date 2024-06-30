<template>
  <header>
    <main>
      <img
        alt="Vue logo"
        class="logo"
        src="@/assets/piggy_bank_logo.png"
        width="125"
        height="125"
      />

      <div class="wrapper">
        <LandingPage msg="Expense Tracker App" />

        <nav>
          <RouterLink to="/">Features</RouterLink>
          <RouterLink to="/about">About</RouterLink>
          <RouterLink v-if="!authenticated" to="/auth">Join Us</RouterLink>
          <button v-if="authenticated" @click="logout">Logout ({{ authStore.getUsername }})</button>
        </nav>
      </div>
    </main>
  </header>

  <div>
    <nav v-if="authenticated">
      <RouterLink to="/expenses/create">Add new</RouterLink>
      <RouterLink to="/expenses/index">View all</RouterLink>
      <RouterLink to="/expenses/analyse">Analyse</RouterLink>
    </nav>
    <RouterView />
  </div>
</template>

<script setup>
import { RouterLink, RouterView } from "vue-router";
import LandingPage from "./components/LandingPage.vue";
import { useAuthStore } from '@/stores/authStore';
import router from "./router";
import { computed } from 'vue';

const authStore = useAuthStore();

function logout() {
  authStore.clearData();
  router.push('/auth');
}

const authenticated = computed(() => {
  return 0 != authStore.getUserId;
});
</script>

<style scoped>
header {
  line-height: 1.5;
  max-height: 100vh;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: white;
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

button {
  background: none;
  border: none;
  font-family: arial, sans-serif;
  cursor: pointer;
  padding: 3px 15px;
  border-left: 1px solid var(--color-border);
  text-decoration: none;
  color: rgba(255, 123, 172, 1);
  transition: 0.4s;
  font-size: 15px;
  text-align: center;
}

button:hover {
  background-color: rgba(255, 123, 172, 0.2);
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem;
  }
}
</style>
