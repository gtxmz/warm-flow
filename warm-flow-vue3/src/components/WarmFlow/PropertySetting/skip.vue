<template>
  <div>
    <el-form ref="formRef" :model="form" label-width="120px" size="small" :disabled="disabled">
      <slot name="form-item-task-skipName" v-if="skipConditionShow" :model="form" field="skipName">
        <el-form-item label="跳转名称">
          <el-input v-model="form.skipName" placeholder="跳转名称"/>
        </el-form-item>
      </slot>
      <slot name="form-item-task-skipType" :model="form" field="skipType">
        <el-form-item label="跳转类型">
          <el-select v-model="form.skipType">
            <el-option sel label="审批通过" value="PASS"/>
            <el-option label="退回" value="REJECT"/>
          </el-select>
        </el-form-item>
      </slot>
      <slot name="form-item-task-skipCondition" v-if="skipConditionShow" :model="form" field="skipCondition">
        <el-form-item label="跳转条件">
          <el-input v-model="form.condition" v-if="!spelFlag" placeholder="条件名" style="width: 20%"/>
          <el-select v-model="form.conditionType" placeholder="请选择条件方式" style="width: 35%;margin-left: 1%" @change="changeOper">
            <el-option label="大于" value="gt"/>
            <el-option label="大于等于" value="ge"/>
            <el-option label="等于" value="eq"/>
            <el-option label="不等于" value="ne"/>
            <el-option label="小于" value="lt"/>
            <el-option label="小于等于" value="le"/>
            <el-option label="包含" value="like"/>
            <el-option label="不包含" value="notNike"/>
            <el-option label="spel表达式" value="spel"/>
          </el-select>
          <el-input v-model="form.conditionValue" placeholder="条件值" style="width: 42%;margin-left: 1%;margin-right: 1%;"/>
        </el-form-item>
      </slot>
    </el-form>
  </div>
</template>

<script setup name="Skip">

const props = defineProps({
  modelValue: {
    type: Object,
    default() {
      return {}
    }
  },
  disabled: { // 是否禁止
    type: Boolean,
    default: false
  },
  skipConditionShow: { // 是否显示跳转条件
    type: Boolean,
    default: true
  },
});

const spelFlag = ref(false);
const form = ref(props.modelValue);
const emit = defineEmits(["change"]);

watch(() => form, n => {
  n = n.value;
  let skipCondition = n.skipCondition;
  skipCondition = "@@" + n.conditionType + "@@|";
  if (n.conditionType !== 'spel') {
    skipCondition = skipCondition
      + (n.condition ? n.condition : '') + "@@"
      + n.conditionType + "@@";
  }
  n.skipCondition = skipCondition
    + (n.conditionValue ? n.conditionValue : '')
  if (n) {
    emit('change', n)
  }
}, {deep: true});

function changeOper(obj) {
  spelFlag.value = obj === 'spel';
}

if (props.modelValue?.conditionType === 'spel') spelFlag.value = true;

</script>

<style scoped>

</style>
