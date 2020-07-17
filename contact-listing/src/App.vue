<template>
  <div id="app">
    <div></div>
    <div class="flex items-center text-center flex-row rounded-lg">
      <div>
        <input
          class="py-2 px-4 rounded-full bg-gray-200 h-8 m-2"
          placeholder="search contact"
          type="text"
          name="search"
          id
          v-model="query"
        />
      </div>
      <div>
        <input
          class="bg-blue-500 text-white font-bold py-2 px-4 rounded-full m-2"
          type="button"
          value="Search"
          v-on:click="searchContact"
        />
      </div>
      <div>
        <input
          type="button"
          class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 m-2 rounded-full"
          v-on:click="addContact"
          value="Add Contact"
        />
      </div>
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
      email: null,
      query: null
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
    },
    searchContact() {
      const _self = this;
      _self.contacts = [];
      if(_self.query){
        Axios.get("http://localhost:8080/search?query="+_self.query).then(response => {
          _self.contacts = response.data;

          console.log("response", _self.contacts);
        });
      }
      else{
        _self.getAllContacts();
      }
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
