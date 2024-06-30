<template>
  <WelcomeItem>
    <template #icon>
      <DocumentationIcon />
    </template>
    <template #heading>Log Expenses</template>

    <p>Start accummulating spending data by regularly adding your expenses.</p>
  </WelcomeItem>

  <WelcomeItem>
    <template #icon>
      <ToolingIcon />
    </template>
    <template #heading>Track Spending Habits</template>

    <p>Separate expenses in different categories and monitor your spending habits.</p>
  </WelcomeItem>

  <WelcomeItem>
    <template #icon>
      <EcosystemIcon />
    </template>
    <template #heading>View Data Analytics</template>

    <p>Once enough data is accumulated start analysing it with our graphs.</p>
  </WelcomeItem>

  <WelcomeItem>
    <template #icon>
      <CommunityIcon />
    </template>
    <template #heading>Got questions?</template>

    Need help? Contact us at <a href="https://twitter.com/vuejs" target="_blank" rel="noopener">support@expensetracker.io</a>
    or visit our <a href="https://twitter.com/vuejs" target="_blank" rel="noopener">FAQ</a> page.
  </WelcomeItem>

  <WelcomeItem>
    <template #icon>
      <SupportIcon />
    </template>
    <template #heading>Support Us</template>

    Currently we don't accept any donations, however you can support us by sharing this website with anyone who might benefit from our services. Thanks!
  </WelcomeItem>
</template>

<script setup>
import WelcomeItem from './WelcomeItem.vue';
import DocumentationIcon from './icons/IconDocumentation.vue';
import ToolingIcon from './icons/IconTooling.vue';
import EcosystemIcon from './icons/IconEcosystem.vue';
import CommunityIcon from './icons/IconCommunity.vue';
import SupportIcon from './icons/IconSupport.vue';
</script>
