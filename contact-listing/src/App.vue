<template>
  <div id="app">
    <div>
      <input type="button" v-on:click="addContact" value="Add Contact" />
    </div>
    <table>
      <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
      </tr>
      <tr v-for="(contact, id) in contacts" :key="id">
        <td>{{contact.id}}</td>
        <td>{{contact.name}}</td>
        <td>{{contact.email}}</td>
      </tr>
    </table>

    <div id="confirmationModalPopup" class="modal hidden info-modal-border-shadow">
      <!-- Modal content -->
      <div class="cv-modal bg-white p-4 z-50 text-center w-screen left-0 bottom-0 outline-none">
        <div class="flex flex-col">
          <h1 class="text-xl font-bold pt-2">Add Contact</h1>

          <input type="text" v-model="name" placeholder="Enter Name" />
          <input type="text" v-model="email" placeholder="Enter email" />
          <input type="button" v-on:click="saveContact" value="Save" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Axios from "axios";
export default {
  name: "App",
  data() {
    return {
      contacts: [],
      toggleModal: false,
      name: null,
      email: null
    };
  },
  created() {
    this.getAllContacts();
  },
  mounted() {
    this.toggleModal = document.getElementById("confirmationModalPopup");
    this.toggleModal.style.display = "none";
  },
  methods: {
    getAllContacts() {
      const _self = this;
      Axios.get("http://localhost:8080/contacts").then(response => {
        console.log("resp", response.data);
        _self.contacts = response.data;

        console.log("resp", _self.contacts);
      });
    },
    addContact() {
      this.toggleModal.style.display = "flex";
    },
    saveContact() {
      const _self = this;
      let httpOptions = {
        headers: { "Content-Type": "application/json" }
      };
      let contact = {
        name: _self.name,
        email: _self.email
      };
      Axios.post("http://localhost:8080/contact", contact, httpOptions).then(
        response => {
          console.log("response", response.data);
          _self.toggleModal = document.getElementById("confirmationModalPopup");
          _self.toggleModal.style.display = "none";
          _self.getAllContacts();
        }
      );
    }
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td,
th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
