<template>
  <div>
    <el-drawer
      ref="drawerRef"
      :title="title"
      destroy-on-close
      v-model="drawer"
      direction="rtl"
      :append-to-body="true"
      :before-close="handleClose">
      <component :is="componentType" v-model="form" :disabled="disabled" :skipConditionShow="skipConditionShow">
        <template v-slot:[key]="data" v-for="(item, key) in $slots">
          <slot :name="key" v-bind="data || {}"></slot>
        </template>
      </component>
    </el-drawer>
  </div>
</template>

<script setup name="Definition">
import start from './start.vue'
import between from './between.vue'
import serial from './serial.vue'
import parallel from './parallel.vue'
import end from './end.vue'
import skip from './skip.vue'

const COMPONENT_LIST = {
  start,
  between,
  serial,
  parallel,
  end,
  skip
}

const props = defineProps({
  value: {
    type: Object,
    default () {
      return {}
    }
  },
  node: {
    type: Object,
    default () {
      return {}
    }
  },
  lf: {
    type: Object,
    default () {
      return null
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

const drawer = ref(false);
const form = ref({});
const objId = ref(undefined);
const nodeCode = ref(null);
const title = computed(() => {
  if (props.node && props.node.type === 'skip') {
    return '设置边属性'
  } else if (props.node && props.node.type === 'serial') {
    return '设置串行网关属性'
  } else if (props.node && props.node.type === 'parallel') {
    return '设置并行网关属性'
  } else if (props.node && props.node.type === 'start') {
    return '设置开始属性'
  } else if (props.node && props.node.type === 'end') {
    return '设置结束属性'
  }
  return '设置中间属性'
});
// 组件类型
const componentType = computed(() => {
  if (!props.node || !props.node.type) return
  return COMPONENT_LIST[props.node.type]
})


watch(() => props.node, n => {
  if (n) {
    objId.value = n.id
    if (n.type === 'skip') {
      let skipCondition = n.properties.skipCondition
      let conditionSpl = skipCondition ? skipCondition.split('@@|') : []
      let conditionSplTwo = conditionSpl && conditionSpl.length > 0 ? conditionSpl[1]: []
      let condition, conditionType, conditionValue = '';
      if (conditionSpl && conditionSpl.length > 0 && conditionSpl[0] === '@@spel') {
        conditionType = 'spel'
        conditionValue = conditionSplTwo
      } else if (conditionSpl && conditionSpl.length > 0 && conditionSpl[0] !== '@@spel') {
        condition = conditionSplTwo && conditionSplTwo.length > 0 ? conditionSplTwo.split("@@")[0] : ''
        conditionType = conditionSplTwo && conditionSplTwo.length > 0 ? conditionSplTwo.split("@@")[1] : ''
        conditionValue = conditionSplTwo && conditionSplTwo.length > 0 ? conditionSplTwo.split("@@")[2] : ''
      }
      form.value = {
        nodeType: n.type,
        skipType: n.properties.skipType,
        skipName: n.text instanceof Object ? n.text.value : n.text,
        skipCondition: skipCondition,
        condition: condition,
        conditionType: conditionType,
        conditionValue: conditionValue
      }
    } else {
      let nodeRatio = n.properties.nodeRatio || "";
      if (!n.properties.collaborativeWay) {
        n.properties.collaborativeWay = nodeRatio === "0.000" ? "1" : nodeRatio === "100.000" ? "3" : nodeRatio ? "2" : "1";
      }
      n.properties.formCustom = JSON.stringify(n.properties) === "{}" ? "N" : (n.properties.formCustom || "");
      form.value = {
        nodeType: n.type,
        nodeCode: n.id,
        ...n.properties,
        nodeName: n.text instanceof Object ? n.text.value : n.text
      }
    }
  }
});

watch(() => form.value.nodeCode, (n) => {
  nodeCode.value = n;
});

watch(() => form.value.skipType, (n) => {
  // 监听跳转属性变化并更新
  props.lf.setProperties(objId.value, {
    skipType: n
  })

});

watch(() => form.value.nodeName, (n) => {
  // 监听节点名称变化并更新
  props.lf.updateText(objId.value, n)
  // 监听节点名称变化并更新
  props.lf.setProperties(objId.value, {
    nodeName: n
  })
});

watch(() => form.value.collaborativeWay, (n) => {
  // 监听节点属性变化并更新
  props.lf.setProperties(objId.value, {
    nodeRatio: n === "1" ? "0.000" : n === "3" ? "100.000" : ""
  })
});

watch(() => form.value.nodeRatio, (n) => {
  // 监听节点属性变化并更新
  props.lf.setProperties(objId.value, {
    nodeRatio: n
  })
});

watch(() => form.value.permissionFlag, (n) => {
  // 监听节点属性变化并更新
  props.lf.setProperties(objId.value, {
    permissionFlag: Array.isArray(n) ? n.filter(e => e).join(',') : n
  })
}, { deep: true });

watch(() => form.value.skipAnyNode, (n) => {
  // 监听跳转属性变化并更新
  props.lf.setProperties(objId.value, {
    skipAnyNode: n
  })
});
watch(() => form.value.listenerType, (n) => {
  // 确保 n 是一个数组
  if (!Array.isArray(n)) {
    n = [n]; // 将 n 转换为数组
  }

  // 将数组元素连接为字符串
  let listenerTypeStr = n.join(",");
  // 监听监听器类型变化并更新
  props.lf.setProperties(objId.value, {
    listenerType: listenerTypeStr
  })
});

watch(() => form.value.listenerPath, (n) => {
  // 监听监听器路径变化并更新
  props.lf.setProperties(objId.value, {
    listenerPath: n
  })
});

watch(() => form.value.formCustom, (n) => {
  props.lf.setProperties(objId.value, {
    formCustom: n || ""
  })
});

watch(() => form.value.formPath, (n) => {
  props.lf.setProperties(objId.value, {
    formPath: n
  })
});

watch(() => form.value.skipName, (n) => {
  if (['skip'].includes(props.node.type)) {
    // 监听跳转名称变化并更新
    props.lf.updateText(objId.value, n)
    // 监听跳转属性变化并更新
    props.lf.setProperties(objId.value, {
      skipName: n
    })
  }
});

watch(() => form.value.skipCondition, (n) => {
  // 监听跳转属性变化并更新
  props.lf.setProperties(objId.value, {
    skipCondition: n
  })

});


function show () {
  drawer.value = true
}

function handleClose () {
  // 监听节点编码变量并更新
  if (nodeCode.value && objId.value) {
    if (['skip'].includes(props.node?.type)) {
      if (!props.lf.getEdgeModelById(nodeCode.value)) {
        props.lf.changeEdgeId(objId.value, nodeCode.value)
      }
    } else {
      if (!props.lf.getNodeModelById(nodeCode.value)) {
        props.lf.changeNodeId(objId.value, nodeCode.value)
      }
    }
  }
  drawer.value = false
}

defineExpose({
  show,
  handleClose
})
</script>

<style scoped>
.el-drawer__container ::-webkit-scrollbar {
  display: none;
}
</style>
